package me.hyperperform.rest;

import me.hyperperform.Hash;
import me.hyperperform.notifications.Email;
import me.hyperperform.reporting.IReport;
import me.hyperperform.reporting.request.GetScoreRequest;
import me.hyperperform.reporting.response.GetScoreResponse;
import me.hyperperform.user.EmployeeRole;
import me.hyperperform.user.Position;
import me.hyperperform.user.User;
import me.hyperperform.user.request.ForgotPasswordRequest;
import me.hyperperform.user.request.GetManagedListRequest;
import me.hyperperform.user.request.VerifyLoginRequest;
import me.hyperperform.user.request.VerifySignUpRequest;
import me.hyperperform.user.response.ForgotPasswordResponse;
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
 * Provides registration and login functionality through REST endpoints.
 *
 * @author : CodusMaximus
 * @version : 1.0
 * @since : 2016/09/09
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

    /**
     * Allows for authentication of users. Authorized users are granted access while unauthorized users are not.
     *
     * @param log - Necessary user information that needs to be validated.
     */
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


    /**
     * Allows for new user to be registered. Once user details have been persisted they can login and use the system.
     *
     * @param jsonStr - Details of the new user to be added to the system.
     */
    @POST
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
             "<td style=\"background-color: rgba(0,0,0, 0.85);\">" +
                "<div style=\"margin-left:28%;\">" +
                     "<img src=\"https://dashboard.hyperperform.me/assets/hyperperform_text.png\" height=\"100px\" alt=\"Logo\" style=\"display: inline; alt=\"HyperPerform\" margin-left: -1.5%;\">" +
                "</div>" +
                    "<br/><br/>" +
            "</td>" +
        "</tr>" +
        "<tr style=\"position: absolute; margin-top: -2%\">" +
        "<td valign=\"top\">" +
            "<table cellpadding=\"0\" cellspacing=\"0\" width=\"682px\" style=\"width:682px;height:263px;background-color:#fff;border-left:solid 1px #999894;border-right:solid 1px #999894\">" +
            "<tbody>" +
        "<tr>" +
        "<td valign=\"top\" align=\"left\">" +
            "<table cellpadding=\"25\" width=\"90%\">" +
            "<tbody>" +
        "<tr>" +
        "<td>" +
        "<h3> Dear <span style=\"color: #999894\">" + sign.getUserName() + "</span></h3>" +
        "<p> You have been added to the HyperPerform system. To view your performance, please navigate <a href=\"https://dashboard.hyperperform.me\"> here </a> and login with the details given below:</p>" +
        "<p>Email: " + sign.getUserEmail() + "</p>" +
        "<p>Password: " + sign.getUserPassword() + "</p>" +
                "<br/><br/>" +
                "Kind Regards<br/>" +
                "<b>HyperPerform Team</b><br/>" +
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
            mail.sendMail(sign.getUserEmail(), "Welcome to HyperPerform " + sign.getUserName(), body);
        }
        else mail.sendMail(sign.getUserEmail(), "Welcome to HyperPerform " + sign.getUserName(), body);


        return Response.status(200).entity("Success").build();

    }

    /**
     * Returns a list of all employees registered excluding the managers. A PA score is also calculated for each of the
     * employees.
     */
    @POST
    @Path("/getManagedList")
    @Consumes("application/json")
    @Produces("application/json")
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

    /**
     * Returns a list of all developer positions within the system.
     */
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

    /**
     * Returns a list of all the employee roles within the system.
     */
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

    /**
     * Creates new password for user in the event they forget theirs.
     */
    @POST
    @Path("/forgotPassword")
    @Consumes("application/json")
    @Produces("application/json")
    public Response forgotPassword(ForgotPasswordRequest forgot)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
        System.out.println("\n\n\n"+forgot.getEmail());
        Query q = entityManager.createQuery("SELECT u FROM User u WHERE userEmail=:email").setParameter("email", forgot.getEmail());
        List<User> list = q.getResultList();

        ForgotPasswordResponse res = null;
        String pass = Hash.randPass(6);
        System.out.println("\n\n Size: " + list.size() );
        if (list.size() == 1)
        {
            System.out.println("\n\n Update");
            q = entityManager.createQuery("UPDATE User SET userPassword=:pass WHERE userEmail=:email")
                    .setParameter("email", forgot.getEmail())
                    .setParameter( "pass", Hash.gethash(pass));

            entityTransaction.begin();
            q.executeUpdate();
            entityTransaction.commit();

            if (mail == null )
             mail = new Email();

            String body = "<div>" +
                    "<div class=\"center\">" +
                    "<table cellpadding=\"0\" cellspacing=\"0\" width=\"682px\">" +
                    "<tbody>" +
                    "<tr>" +
                    "<td style=\"background-color: rgba(0,0,0, 0.85);\">" +
                    "<div style=\"margin-left:28%;\">" +
                    "<img src=\"https://dashboard.hyperperform.me/assets/hyperperform_text.png\" height=\"100px\" alt=\"Logo\" style=\"display: inline; alt=\"HyperPerform\" margin-left: -1.5%;\">" +
                    "</div>" +
                    "<br/><br/>" +
                    "</td>" +
                    "</tr>" +
                    "<tr style=\"position: absolute; margin-top: -2%\">" +
                    "<td valign=\"top\">" +
                    "<table cellpadding=\"0\" cellspacing=\"0\" width=\"682px\" style=\"width:682px;height:263px;background-color:#fff;border-left:solid 1px #999894;border-right:solid 1px #999894\">" +
                    "<tbody>" +
                    "<tr>" +
                    "<td valign=\"top\" align=\"left\">" +
                    "<table cellpadding=\"25\" width=\"90%\">" +
                    "<tbody>" +
                    "<tr>" +
                    "<td>" +
                    "<h3> Dear <span style=\"color: #999894\">" + list.get(0).getName() + "</span> </h3>" +
                    "<p> You have requested a new password for our HyperPerform system. Please find your details below to login:</p>" +
                    "<p>Email: " + list.get(0).getUserEmail() + "</p>" +
                    "<p>Password: " + pass + "</p>" +
                    "<br/>" +
                    "Kind Regards<br/>" +
                    "<b>HyperPerform Team</b><br/>" +
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
            res = new ForgotPasswordResponse("Success, you will shortly receive an email");
            mail.sendMail(forgot.getEmail(), "Hello " + list.get(0).getName(), body);

        }
        else
        {
            res = new ForgotPasswordResponse("Error: You are not registered with HyperPerform");
        }


        return Response.status(200).entity(res).build();
    }

}
