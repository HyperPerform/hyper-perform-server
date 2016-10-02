package me.hyperperform.listener;

import me.hyperperform.QueueConnection;
import me.hyperperform.event.EntryExit.AccessEvent;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/20
 * Feature:
 */

/**
 * Access event listener class which exposes a REST link for any entry/exit system to send events to.
 * Each event received will be stored in a database and will be processed at a later stage. Each
 * access event must comply with the event POJO defined at {@see me.hyperperform.event.EntryExit.AccessEvent}
 * This class implements the {@see me.hyperperform.listener.IListener} interface.
 */
@Path("/AccessEvent")
public class AccessListener implements IListener
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
     * Listener function which waits for events. Each received event is automatically mapped onto a
     * {@see me.hyperperform.event.EntryExit.AccessEvent} POJO. The events come in primarily as JSON objects.
     * @param accessEvent The access event event that has taken place. The JSON object is mapped to this Java object
     *                    automatically.
     * @return Returns a HTTP 200 response code if the event was successfully mapped and persisted.
     */
    @POST
        @Consumes("application/json")
        public Response listen(AccessEvent accessEvent) throws Exception {
        System.out.println("\nAccess Event\n\n");

            if (accessEvent != null)
            {

                if (accessEvent.getEmployeeID() != null || !accessEvent.getEmployeeID().equals(""))
                {

                    if (queueConnection != null)
                        queueConnection.sendObject(accessEvent);

                    if (entityManager != null)
                    {
                        entityManager.getTransaction().begin();

                        entityManager.persist(accessEvent);

                        entityManager.getTransaction().commit();
                    } else System.out.println("error Entity Man");
                }
            }

            return Response.status(200).entity("Successfully received Access event").header("Access-Control-Allow-Origin", "*").build();
        }
}
