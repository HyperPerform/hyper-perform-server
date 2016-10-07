package me.hyperperform.rest;

import me.hyperperform.Hash;
import me.hyperperform.notifications.Email;
import me.hyperperform.reporting.IReport;
import me.hyperperform.reporting.request.GetScoreRequest;
import me.hyperperform.reporting.response.GetScoreResponse;
import me.hyperperform.user.EmployeeRole;
import me.hyperperform.user.Position;
import me.hyperperform.user.User;
import me.hyperperform.user.request.GetManagedListRequest;
import me.hyperperform.user.request.VerifyLoginRequest;
import me.hyperperform.user.request.VerifySignUpRequest;
import me.hyperperform.user.response.GetManagedListResponse;
import me.hyperperform.user.response.VerifyLoginResponse;
import me.hyperperform.user.response.VerifySignUpResponse;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.inject.Inject;
import javax.persistence.*;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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

    @Inject
    IReport reportGenerator;

    @Inject
    Email mail;

    @POST
    @Path("/verifyDetails")
    @Produces("application/json")
    @Consumes("application/json")
    public Response verifyLogin(VerifyLoginRequest log)
    {

        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();

        log.setUserEmail(log.getUserEmail().toLowerCase());

        System.out.print("\n"+log.getUserEmail() + " " + log.getUserPassword() + "\n");

        VerifyLoginResponse res = null;
        Query query = entityManager.createQuery("SELECT u FROM User u WHERE userEmail = :input", User.class)
                .setParameter("input", log.getUserEmail());

        List<User> result = query.getResultList();

        for (int i = 0; i < result.size(); i++)
        {
            User tmp = result.get(i);

            if (log.getUserEmail().equals(tmp.getUserEmail()))
            {
                if (Hash.gethash(log.getUserPassword()).equals(tmp.getUserPassword()))
                {
                    res = new VerifyLoginResponse(true, tmp.getUserEmail(), tmp.getName(), tmp.getPosition());
                }
            }
        }

        if (res != null)
            System.out.println("Response: " + res.getLoggedin());
        else System.out.println("Response: null");
        return Response.status(200).entity(res).build();

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
        sign.setUserEmail(sign.getUserEmail().toLowerCase());

        sign.setUserPassword((String)json.get("userPassword"));
        sign.setPosition((String)json.get("position"));
        sign.setRole((String)json.get("role"));
        sign.setGitUserName((String) json.get("gitUserName"));

//        System.out.println("--------------------------------------------------");
//        System.out.println(sign);
//        System.out.println("--------------------------------------------------");

        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();

        VerifySignUpResponse res = null;

        if (sign.getUserName() == null || sign.getUserName().equals(""))
            return  Response.status(200).entity("Error: name").build();

        if (sign.getUserSurname() == null || sign.getUserSurname().equals(""))
            return  Response.status(200).entity("Error: surname").build();

        if (sign.getUserEmail() == null || sign.getUserEmail().equals(""))
            return  Response.status(200).entity("Error: email").build();

        if (sign.getUserPassword() == null || sign.getUserPassword().equals(""))
            return  Response.status(200).entity("Error: password").build();

        if (sign.getRole() == null || sign.getRole().equals(""))
            return  Response.status(200).entity("Error: role").build();

        if (sign.getPosition() == null || sign.getPosition().equals(""))
            return Response.status(200).entity("Error: position").build();

        try {
            EmployeeRole.valueOf(sign.getRole());
        }

        catch (IllegalArgumentException e) {
            return  Response.status(200).entity("Error: Role does not exist").build();
        }

        try {
            Position.valueOf(sign.getPosition());
        }

        catch (IllegalArgumentException e) {
            return  Response.status(200).entity("Error: Position does not exist").build();
        }


        Query query = entityManager.createQuery("SELECT u FROM User u WHERE userEmail=:email").setParameter("email", sign.getUserEmail());
        List<User> result = query.getResultList();

        if (result.size() != 0)
            return  Response.status(200).entity("Error: Email Already Exists").build();


        User user = new User();
        user.setName(sign.getUserName());
        user.setSurname(sign.getUserSurname());
        user.setUserEmail(sign.getUserEmail());
        user.setUserPassword(sign.getUserPassword());
        user.setPosition(Position.valueOf(sign.getPosition()));
        user.setRole(EmployeeRole.valueOf(sign.getRole()));
        user.setGitUserName(sign.getGitUserName());

        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();

        String body = "<div>" +
        "<div class=\"center\">" +
        "<table cellpadding=\"0\" cellspacing=\"0\" width=\"682px\">" +
            "<tbody>" +
        "<tr>" +
        "<td>" +
        "<div class=\"log-title\" align=\"center\">" +
            "<img class=\"main-logo\" src=\"assets/Logo2.png\" height=\"50px\" alt=\"Logo\" style=\"display: inline;\">" +
        "<span id=\"hyper\" style=\"color: #1F968B;\"> Hyper</span><span id=\"perform\">Perform</span>" +
        "</div>" +
            "</td>" +
        "</tr>" +
        "<tr>" +
        "<td valign=\"top\">" +
            "<table cellpadding=\"0\" cellspacing=\"0\" width=\"682px\" style=\"width:682px;height:263px;background-color:#fff;border-left:solid 1px #999894;border-right:solid 1px #999894\">" +
            "<tbody>" +
        "<tr>" +
        "<td valign=\"top\" align=\"left\">" +
            "<table cellpadding=\"25\" width=\"90%\">" +
            "<tbody>" +
        "<tr>" +
        "<td>" +
        "<h3> Dear " + sign.getUserName() + " </h3>" +
        "<p> You have been added to the HyperPerform system please find your details below to login:</p>" +
        "<p>Email: " + sign.getUserEmail() + "</p>" +
        "<p>Password: " + sign.getUserPassword() + "</p>" +
                "<br/><br/><br/>" +
                "Kind Regards<br/>" +
                "<b>Hyperperformteam</b><br/>" +
                "<img src='https://dashboard.hyperperform.me/assets/Logo2.png' height='30' width='30' style='top: 2%;text-align: center;'>" +
        "</td>" +
        "</tr>" +
        "</tbody>" +
        "</table>" +
        "</td>" +
        "</tr>" +
        "</tbody>" +
        "</table>" +
        "</td>" +
        "</tr>" +
        "</tbody>" +
        "</table>" +
        "</div>" +
        "</div>";
//        String body = "<!DOCTYPE html>" +
//                "<html>" +
//                "" +
//                "<body>" +
//                "<h2>Dear: <span style='color: #3C878D'>" + sign.getUserName() + "</span></h2>" +
//                "You have recently been added to our system.<br/>" +
//                "Please find your password at the bottom<br/>" +
//                "<br/>" +
//                "<a href='https://dashboard.hyperperform.me'><i>Visit Dashboard</i></a><br/><br/>" +
//                "Password: <b>" + sign.getUserPassword() + " </b>" +
//                "<br/><br/><br/>" +
//                "Kind Regards<br/>" +
//                "<b>Hyperperformteam</b><br/>" +
//                "<img src='https://dashboard.hyperperform.me/assets/Logo2.png' height='30' width='30' style='top: 2%;text-align: center;'>" +
//                "</body>" +
//                "</html>";

        if (mail == null)
        {
            mail = new Email();
            mail.sendMail(sign.getUserEmail(), "Welcome to Hyperperform " + sign.getUserName(), body);
        }
        else mail.sendMail(sign.getUserEmail(), "Welcome to Hyperperform " + sign.getUserName(), body);


        return Response.status(200).entity("Success").build();

    }

    @POST
    @Path("/getManagedList")
    @Consumes("application/json")
    @Produces("application/json")

//    public Response getManagedList(GetManagedListRequest getManagedListRequest)
    public Response getManagedList(GetManagedListRequest getManagedListRequest)
    {
//        GetManagedListRequest getManagedListRequest = new GetManagedListRequest();
//        getManagedListRequest.setStartDate("2016-01-01 00:00:01");
//        getManagedListRequest.setEndDate("2016-12-30 23:59:59");

        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();

        GetManagedListResponse getManagedListResponse = new GetManagedListResponse();

        Query q = entityManager.createQuery("select u from User u where position != 3");
        List<User> list = q.getResultList();

        int n = list.size();
        GetScoreRequest getScoreRequest = new GetScoreRequest();
        getScoreRequest.setStartDate(getManagedListRequest.getStartDate().toString());
        getScoreRequest.setEndDate(getManagedListRequest.getEndDate().toString());

        for (int k = 0; k < n; k++) {
            getScoreRequest.setName(list.get(k).getUserEmail());
            GetScoreResponse getScoreResponse = reportGenerator.getScore(getScoreRequest);

            getManagedListResponse.addToList(list.get(k).getName(),
                    list.get(k).getSurname(),
                    list.get(k).getUserEmail(),
                    getScoreResponse.getScore(),
                    getScoreResponse.getPerformance(),
                    (list.get(k).getPosition() == null) ? "Unknown" : list.get(k).getPosition().getType());
        }

        getManagedListResponse.setSize(n);

        return Response.status(200).entity(getManagedListResponse).build();
    }

    @GET
    @Path("/getPositions")
    @Produces("application/json")
    public Response getPositions()
    {
        ArrayList<String> list = new ArrayList<String>();
        Position[] positions = Position.values();

        for (int k = 0; k < positions.length; k++)
            list.add(positions[k].getType());

        return Response.status(200).entity(list).build();
    }

    @GET
    @Path("/getRoles")
    @Produces("application/json")
    public Response getRoles()
    {
        ArrayList<String> list = new ArrayList<String>();
        EmployeeRole[] roles = EmployeeRole.values();

        for (int k = 0; k < roles.length; k++)
        {
            if (!roles[k].getType().equals("Super"))
                list.add(roles[k].getType());
        }
        return Response.status(200).entity(list).build();
    }
}
