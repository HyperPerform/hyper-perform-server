package me.hyperperform.listener;

import me.hyperperform.QueueConnection;
import me.hyperperform.event.Git.GitIssue;
import me.hyperperform.event.Git.GitPush;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.inject.Inject;

import javax.persistence.*;

import javax.ws.rs.core.Response;
import javax.ws.rs.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.sql.Timestamp;


/**
 * Provides a URL for GitHub to push events to. From here the events are persisted and placed onto a queue
 * for CEP at a later stage. This listener handle two types of events: push and issues. Both events are mapped to
 * the {@see me.hyperperform.event.Git.Push} and {@see me.hyperperform.event.Git.GitIssue} respectively. This class
 * implements the {@see me.hyperperform.event.listener.IListener} interface.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/07/05
 */

@Path("/gitEvent")
public class GitListener implements IListener
{
    /**
     * Connection to the messaging queue. The object is provided through dependency injection.
     */
    @Inject
    QueueConnection queueConnection;

    /**
     * Persistence context which allows for persisting the events received.
     */
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
     * Listener function which receives the Git events. Each type of event is mapped to the corresponding POJO. Once
     * mapped, the event is added to the database for processing at ta later stage. Events received are in the form
     * of a JSON object.
     *
     * @param eventType This field identifies the type of GitHub event coming through i.e push, pull, issue closed etc.
     * @param jsonStr Contains the actual information about the event. This JSON object is processed and persisted.
     * @return If mapping and persistence was successful a 200 status code is returned.
     * @throws Exception if there was an error in processing the data
     */
    @POST
    @Consumes("application/json")
    public Response listen(@HeaderParam("X-GitHub-Event") String eventType, String jsonStr) throws Exception
    {
        JSONObject json = (JSONObject)new JSONParser().parse(jsonStr);

        if (eventType.equals("push"))
        {
           JSONObject repository = (JSONObject)json.get("repository");
           JSONObject commit = (JSONObject)json.get("head_commit");
           JSONObject pusher = (JSONObject)json.get("pusher");
           JSONArray commits = (JSONArray)json.get("commits");
           JSONObject headCommit = (JSONObject) json.get("head_commit");

           String name = (String)repository.get("full_name");
           String date = (String)commit.get("timestamp");
           String user = (String)pusher.get("name");
           String url = (String)headCommit.get("url");

           GitPush push = new GitPush(name, extractDate(date) + " " + extractTime(date), user, commits.size());
           push.setUrl(url);
           push.setMessage((String)headCommit.get("message"));

            if (queueConnection != null)
                queueConnection.sendObject(push);

            if (entityManager != null)
            {
                entityManager.getTransaction().begin();
                entityManager.persist(push);
                entityManager.getTransaction().commit();
            }
        }
        else
        if (eventType.equals("issues"))
        {
            GitIssue gitIssue = new GitIssue();

            JSONObject issue = (JSONObject)json.get("issue");
            JSONObject user = (JSONObject)issue.get("user");
            JSONObject repo = (JSONObject)json.get("repository");
            JSONObject assignee = (JSONObject)issue.get("assignee");

            gitIssue.setIssueId((Long)issue.get("id"));
            gitIssue.setAction((String)json.get("action"));
            gitIssue.setRepository((String)repo.get("name"));

//            gitIssue.setCreatedAt(extractTimestamp((String)issue.get("created_at")));
//            gitIssue.setClosedAt(extractTimestamp((String)issue.get("closed_at")));

            gitIssue.setTimestamp(new Timestamp(System.currentTimeMillis()));

            gitIssue.setAssignee((assignee == null) ? null : (String)assignee.get("login"));
            gitIssue.setCreatedBy((String)user.get("login"));

            gitIssue.setUrl((String)issue.get("html_url"));
            gitIssue.setTitle((String)issue.get("title"));

            if (queueConnection != null)
                queueConnection.sendObject(gitIssue);

            if (entityManager != null)
            {
                entityManager.getTransaction().begin();
                entityManager.persist(gitIssue);
                entityManager.getTransaction().commit();
            }
        }

        return Response.status(200).entity("Successfully received event").header("Access-Control-Allow-Origin", "*").build();
    }



//    @GET
//    @Path("/testing")
//    public Response myTest() throws Exception
//    {
//        GitPush gitPush = new GitPush();
//
//        String out = "---Start Debug Output--- <br/>";
//
//        out += "Sending git event object to queue <br/>";
//        queueConnection.sendObject(gitPush);
//
//        out += "Getting object from queue <br/>";
//        out += ((GitPush) queueConnection.receive()).toString() + "<br/>";
//
//        out += "Persistence Context: " + em + "<br/>";
//
//        out += "---End Debug Output--- <br/>";
//
//        return Response.status(200).entity(out).build();
//    }

    private static <T> void log(T t)
    {
        System.out.println(t);
    }

    /**
     * Accepts timestamp as a String. Github event timestamp is not of correct format thus this method has the
     *          ability to extract the date component of the timestamp.
     * @param s Timestamp of the event as a String object.
     * @return Returns the date of the timestamp as a String.
     */
    private String extractDate(String s)
    {
        return (s.indexOf('T') != -1) ? s.substring(0, s.indexOf('T')) : s.substring(0, s.indexOf(' '));
    }

    /**
     *  Extracts the time portion from the timestamp.
     * @param s Timestamp of the event as a String object.
     * @return Returns the time of the timestamp as a String.
     */
    private String extractTime(String s)
    {
        String tmp = s;
        tmp = s.substring(s.indexOf('T')+1);

        if (tmp.indexOf('-') != -1)
            tmp = tmp.substring(0, tmp.indexOf('-'));
        else
        if (tmp.indexOf('+') != -1)
            tmp = tmp.substring(0, tmp.indexOf('+'));
        else
        if (tmp.indexOf('Z') != -1)
            tmp = tmp.substring(0, tmp.indexOf('Z'));

        return tmp;
    }

    private String extractTimestamp(String s)
    {
        if (s == null)
            return null;

        return (extractDate(s) + " " + extractTime(s));
    }
}
