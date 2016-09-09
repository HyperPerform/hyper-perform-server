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
        u = new User("u12345678","Avinash", "Singh", "tashan.avi@gmail.com", "hashedPass",EmployeeRole.Employee ,Position.SoftwareDeveloper );
        admin = new User("admin","admin", "", "admin", "hashedPass", EmployeeRole.Administrator, null);

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
        Assert.assertEquals("Not the same name", "Avinash", u.getUserName());
        Assert.assertEquals("Not the same surname", "Singh", u.getUserSurname());
        Assert.assertEquals("Not the same userID", "u12345678", u.getUserID());
        Assert.assertEquals("Not the same email", "tashan.avi@gmail.com", u.getUserEmail());
        Assert.assertEquals("Not the same password", "hashedPass", u.getUserPassword());
        Assert.assertEquals("Not the same admin", EmployeeRole.Employee , u.getRole());
        Assert.assertEquals("Not the same Position", Position.SoftwareDeveloper, u.getPosition());



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
