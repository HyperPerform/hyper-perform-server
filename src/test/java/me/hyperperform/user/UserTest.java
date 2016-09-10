package me.hyperperform.user;

import org.junit.*;

import javax.imageio.ImageIO;
import javax.persistence.*;

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
        u = new User("tashan.avi@gmail.com","u12345678","Avinash", "Singh", "hashedPass", null, EmployeeRole.Employee, Position.SoftwareDeveloper );
        admin = new User("admin@hyperperform.me","admin", "admin", "", "hashedPass", null, EmployeeRole.Administrator, null);

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




    @After
    public void closeManager()
    {
        entityManager.close();
        entityManagerFactory.close();
    }

}
