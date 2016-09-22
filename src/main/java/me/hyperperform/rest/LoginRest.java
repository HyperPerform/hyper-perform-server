package me.hyperperform.rest;

import me.hyperperform.reporting.request.GetDetailsRequest;
import me.hyperperform.user.EmployeeRole;
import me.hyperperform.user.Position;
import me.hyperperform.user.User;
import me.hyperperform.user.request.VerifyLoginRequest;
import me.hyperperform.user.request.VerifySignUpRequest;
import me.hyperperform.user.response.VerifyLoginResponse;
import me.hyperperform.user.response.VerifySignUpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/09
 * Feature:
 */


@Path("/users")
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
//    @GET
    @Path("/verifySignUp")
    @Produces("application/json")
    @Consumes("application/json")
    public Response verifySignup(String jsonStr) throws Exception
//    public Response verifySignup()
    {

        JSONObject json = (JSONObject)new JSONParser().parse(jsonStr);

        VerifySignUpRequest sign = new VerifySignUpRequest();
        sign.setUserName((String)json.get("userName"));
        sign.setUserSurname((String)json.get("userSurname"));
        sign.setUserEmail((String)json.get("userEmail"));
        sign.setUserPassword((String)json.get("userPassword"));
        sign.setPosition((String)json.get("position"));
        sign.setRole((String)json.get("role"));

//        System.out.println("--------------------------------------------------");
//        System.out.println(sign);
//        System.out.println("--------------------------------------------------");

        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();

        VerifySignUpResponse res = null;

        if (sign.getUserName() == null || sign.getUserName().equals(""))
            return  Response.status(400).entity("Invalid Name").header("Access-Control-Allow-Origin", "*").build();

        if (sign.getUserSurname() == null || sign.getUserSurname().equals(""))
            return  Response.status(400).entity("Invalid Surname").header("Access-Control-Allow-Origin", "*").build();

        if (sign.getUserEmail() == null || sign.getUserEmail().equals(""))
            return  Response.status(400).entity("Invalid Email").header("Access-Control-Allow-Origin", "*").build();

        if (sign.getUserPassword() == null || sign.getUserPassword().equals(""))
            return  Response.status(400).entity("Invalid Password").header("Access-Control-Allow-Origin", "*").build();

        if (sign.getRole() == null || sign.getRole().equals(""))
            return  Response.status(400).entity("No role found").header("Access-Control-Allow-Origin", "*").build();

        if (sign.getPosition() == null || sign.getPosition().equals(""))
            return  Response.status(400).entity("No position found").header("Access-Control-Allow-Origin", "*").build();

        Query query = entityManager.createQuery("SELECT u FROM User u WHERE userEmail=:email").setParameter("email", sign.getUserEmail());
        List<User> result = query.getResultList();

        if (result.size() != 0)
            return  Response.status(400).entity("Email Already Exists").header("Access-Control-Allow-Origin", "*").build();

        try {
            EmployeeRole.valueOf(sign.getRole());
        }

        catch (IllegalArgumentException e) {
            return  Response.status(400).entity("Role doesn't exist").header("Access-Control-Allow-Origin", "*").build();
        }

        try {
            Position.valueOf(sign.getPosition());
        }

        catch (IllegalArgumentException e) {
            return  Response.status(400).entity("Position doesn't exist").header("Access-Control-Allow-Origin", "*").build();
        }

        User user = new User();
        user.setName(sign.getUserName());
        user.setSurname(sign.getUserSurname());
        user.setUserEmail(sign.getUserEmail());
        user.setUserPassword(sign.getUserPassword());
        user.setPosition(Position.valueOf(sign.getPosition()));
        user.setRole(EmployeeRole.valueOf(sign.getRole()));

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        return Response.status(200).entity(res).header("Access-Control-Allow-Origin", "*").build();

    }



}
