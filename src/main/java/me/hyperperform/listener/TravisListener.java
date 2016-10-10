package me.hyperperform.listener;

import me.hyperperform.QueueConnection;

import me.hyperperform.event.Travis.TravisEvent;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.inject.Inject;

import javax.persistence.*;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.lang.StringBuilder;

/**
 * Provides a URL for Travis to push build events to. From here the events are persisted and placed onto a queue
 * for CEP at a later stage.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/07/05
 */

@Path("/TravisEvent")
public class TravisListener implements IListener
{

    @Inject
    QueueConnection queueConnection;

    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    @PostConstruct
    private void initConnection()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @PreDestroy
    private void disconnect()
    {
        entityManager.close();
        entityManagerFactory.close();
    }

    /**
     * Listener function which is called when Travis sends out an event to the provided REST endpoint
     * @param jsonStr The JSON object sent by Travis
     * @return returns a 200 response
     */
    @POST
    @Consumes("application/x-www-form-urlencoded")
    public Response listen(@FormParam("payload") String jsonStr) throws Exception {

        JSONObject json = (JSONObject)new JSONParser().parse(jsonStr);

        TravisEvent t = new TravisEvent();
        t.setCommiter((String)json.get("committer_name"));
        t.setBranch((String)json.get("branch"));
        t.setStatus((String)json.get("status_message"));
        t.setTimestamp(parseTimeStamp((String)json.get("committed_at")));
        t.setRepo((String)((JSONObject)json.get("repository")).get("name"));

        if (t.getStatus().equals("Fixed"))
            t.setStatus("Passed");
        else
        if (t.getStatus().equals("Broken") || t.getStatus().equals("Failed") || t.getStatus().equals("Still Failing"))
            t.setStatus("Failed");

           if (queueConnection != null)
               queueConnection.sendObject(t);

           if (entityManager != null)
           {
               entityManager.getTransaction().begin();

               entityManager.persist(t);

               entityManager.getTransaction().commit();
           }

        return Response.status(200).entity("Successfully received event").header("Access-Control-Allow-Origin", "*").build();
    }

    private static <T> void log(T t)
    {
        System.out.println(t);
    }

    private String parseTimeStamp(String timestamp)
    {
        StringBuilder time = new StringBuilder(timestamp.substring(timestamp.indexOf("T")+1));

        int i = 0;
        while((i = time.indexOf(" ")) != -1)
            time.deleteCharAt(i);

        time.deleteCharAt(time.indexOf("Z"));

        return timestamp.substring(0, timestamp.indexOf("T")) + " " + time.toString();
    }
}
