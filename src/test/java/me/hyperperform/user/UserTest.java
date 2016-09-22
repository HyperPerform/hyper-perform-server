package me.hyperperform.user;

import me.hyperperform.event.MockEvent;
import me.hyperperform.listener.TravisListener;
import me.hyperperform.rest.LoginRest;
import org.jboss.resteasy.core.Dispatcher;
import org.jboss.resteasy.mock.MockDispatcherFactory;
import org.jboss.resteasy.mock.MockHttpRequest;
import org.jboss.resteasy.mock.MockHttpResponse;
import org.jboss.resteasy.plugins.server.resourcefactory.POJOResourceFactory;
import org.junit.*;

import javax.imageio.ImageIO;
import javax.persistence.*;
import javax.ws.rs.core.MediaType;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.util.*;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/05
 * Feature:
 */
public class UserTest
{
    private User u;
    private User admin;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;


    @Test
    @Before
    public void createUserTest() throws Exception
    {
//        Image img = ImageIO.read(new FileInputStream(""));
        Image img = null;

//        String userEmail, String userName, String name, String surname, String userPassword, Byte[] profilePicture, EmployeeRole role, Position position
        u = new User("tashan.avi@gmail.com", "AvinashSingh786" ,"u12345678","Avinash", "Singh", "hashedPass", null, EmployeeRole.Employee, Position.SoftwareDeveloper );
        admin = new User("admin@hyperperform.me", "","admin", "admin", "", "hashedPass", null, EmployeeRole.Administrator, null);

        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();

        Query query = entityManager.createQuery("FROM User ", User.class);
        java.util.List<User> result = query.getResultList();

        if (result.size() == 0)
        {
            entityTransaction.begin();
            entityManager.persist(admin);
            entityManager.persist(u);
            entityTransaction.commit();
        }

    }

    @Test
    public void userTest() throws Exception
    {
        Assert.assertEquals("Not the same username", "u12345678", u.getUserName());
        Assert.assertEquals("Not the same surname", "Singh", u.getSurname());
        Assert.assertEquals("Not the same name", "Avinash", u.getName());
        Assert.assertEquals("Not the same email", "tashan.avi@gmail.com", u.getUserEmail());
        Assert.assertEquals("Not the same password", "hashedPass", u.getUserPassword());
        Assert.assertEquals("Not the same admin", EmployeeRole.Employee , u.getRole());
        Assert.assertEquals("Not the same Position", Position.SoftwareDeveloper, u.getPosition());

        Assert.assertEquals("Not the same username", "admin", admin.getUserName());
        Assert.assertEquals("Not the same surname", "", admin.getSurname());
        Assert.assertEquals("Not the same name", "admin", admin.getName());
        Assert.assertEquals("Not the same email", "admin@hyperperform.me", admin.getUserEmail());
        Assert.assertEquals("Not the same password", "hashedPass", admin.getUserPassword());
        Assert.assertEquals("Not the same admin", EmployeeRole.Administrator , admin.getRole());
        Assert.assertEquals("Not the same Position", null, admin.getPosition());


    }


    @Test
    @Ignore
    public void queryTest() throws Exception
    {
        // query and test
    }

    @Test
    public void registrationTest() throws Exception
    {
        System.out.println("Running valid registration test...");

        POJOResourceFactory noDef = new POJOResourceFactory(LoginRest.class);
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
        dispatcher.getRegistry().addResourceFactory(noDef);

        MockHttpRequest request = MockHttpRequest.post("users/verifySignUp");

        request.contentType(MediaType.APPLICATION_JSON);
        request.content(MockUsers.normalUser.getBytes());

        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        Assert.assertEquals(200, response.getStatus());

        entityTransaction.begin();
        entityManager.createQuery("DELETE FROM User").executeUpdate();
        entityTransaction.commit();
    }

    @Test
    public void invalidNameTest() throws Exception
    {
        System.out.println("Running invalid name registration test...");

        POJOResourceFactory noDef = new POJOResourceFactory(LoginRest.class);
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
        dispatcher.getRegistry().addResourceFactory(noDef);

        MockHttpRequest request = MockHttpRequest.post("users/verifySignUp");

        request.contentType(MediaType.APPLICATION_JSON);
        request.content(MockUsers.noUsername.getBytes());

        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Invalid Name", response.getContentAsString());
    }

    @Test
    public void invalidSurnameTest() throws Exception
    {
        System.out.println("Running invalid surname registration test...");

        POJOResourceFactory noDef = new POJOResourceFactory(LoginRest.class);
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
        dispatcher.getRegistry().addResourceFactory(noDef);

        MockHttpRequest request = MockHttpRequest.post("users/verifySignUp");

        request.contentType(MediaType.APPLICATION_JSON);
        request.content(MockUsers.noSurname.getBytes());

        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Invalid Surname", response.getContentAsString());
    }

    @Test
    public void invalidEmailTest() throws Exception
    {
        System.out.println("Running invalid email registration test...");

        POJOResourceFactory noDef = new POJOResourceFactory(LoginRest.class);
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
        dispatcher.getRegistry().addResourceFactory(noDef);

        MockHttpRequest request = MockHttpRequest.post("users/verifySignUp");

        request.contentType(MediaType.APPLICATION_JSON);
        request.content(MockUsers.noEmail.getBytes());

        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Invalid Email", response.getContentAsString());
    }

    @Test
    public void invalidRoleTest() throws Exception
    {
        System.out.println("Running invalid role registration test...");

        POJOResourceFactory noDef = new POJOResourceFactory(LoginRest.class);
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
        dispatcher.getRegistry().addResourceFactory(noDef);

        MockHttpRequest request = MockHttpRequest.post("users/verifySignUp");

        request.contentType(MediaType.APPLICATION_JSON);
        request.content(MockUsers.invalidRole.getBytes());

        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Role doesn't exist", response.getContentAsString());
    }

    @Test
    public void invalidPositionTest() throws Exception
    {
        System.out.println("Running invalid position registration test...");

        POJOResourceFactory noDef = new POJOResourceFactory(LoginRest.class);
        Dispatcher dispatcher = MockDispatcherFactory.createDispatcher();
        dispatcher.getRegistry().addResourceFactory(noDef);

        MockHttpRequest request = MockHttpRequest.post("users/verifySignUp");

        request.contentType(MediaType.APPLICATION_JSON);
        request.content(MockUsers.invalidPosition.getBytes());

        MockHttpResponse response = new MockHttpResponse();
        dispatcher.invoke(request, response);

        Assert.assertEquals(400, response.getStatus());
        Assert.assertEquals("Position doesn't exist", response.getContentAsString());
    }

    @After
    public void closeManager()
    {
        entityManager.close();
        entityManagerFactory.close();
    }

}
