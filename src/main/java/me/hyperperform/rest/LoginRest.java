package me.hyperperform.rest;

import me.hyperperform.reporting.request.GetDetailsRequest;
import me.hyperperform.user.User;
import me.hyperperform.user.request.VerifyLoginRequest;
import me.hyperperform.user.response.VerifyLoginResponse;

import javax.persistence.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/09
 * Feature:
 */


@Path("/login")
public class LoginRest
{
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;


    @POST
    @Path("/verifyDetails")
    @Produces("application/json")
    @Consumes("application/json")
    public Response verify(VerifyLoginRequest log)
    {

        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();

        VerifyLoginResponse res = null;
        Query query = entityManager.createQuery("FROM User ", User.class);
        List<User> result = query.getResultList();

        for (int i = 0; i < result.size(); i++)
        {
            User tmp = result.get(i);

            if (log.getUserID().equals(tmp.getUserEmail()))
            {
                if (log.getUserID().equals(tmp.getUserPassword()))
                {
                    res = new VerifyLoginResponse(true);
                }
            }
        }


        return Response.status(200).entity(res).header("Access-Control-Allow-Origin", "*").build();

    }


}
