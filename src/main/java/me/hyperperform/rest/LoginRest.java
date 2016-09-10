package me.hyperperform.rest;

import me.hyperperform.reporting.request.GetDetailsRequest;
import me.hyperperform.user.User;
import me.hyperperform.user.request.VerifyLoginRequest;
import me.hyperperform.user.request.VerifySignUpRequest;
import me.hyperperform.user.response.VerifyLoginResponse;
import me.hyperperform.user.response.VerifySignUpResponse;

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
    public Response verifyLogin(VerifyLoginRequest log)
    {

        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();

//        System.out.print(log.getUserEmail() + " " + log.getUserPassword());

        VerifyLoginResponse res = null;
        Query query = entityManager.createQuery("FROM User ", User.class);
        List<User> result = query.getResultList();

        for (int i = 0; i < result.size(); i++)
        {
            User tmp = result.get(i);

            if (log.getUserEmail().equals(tmp.getUserEmail()))
            {
                if (log.getUserPassword().equals(tmp.getUserPassword()))
                {
                    res = new VerifyLoginResponse(true);
                }
            }
        }


        return Response.status(200).entity(res).header("Access-Control-Allow-Origin", "*").build();

    }


    @POST
    @Path("/verifySignUp")
    @Produces("application/json")
    @Consumes("application/json")
    public Response verifySignup(VerifySignUpRequest sign)
    {

        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();

        VerifySignUpResponse res = null;

        //@TODO check the request integrity and persist the new user


        return Response.status(200).entity(res).header("Access-Control-Allow-Origin", "*").build();

    }



}
