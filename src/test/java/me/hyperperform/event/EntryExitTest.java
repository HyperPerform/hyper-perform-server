package me.hyperperform.event;

import me.hyperperform.event.EntryExit.AccessEvent;
import org.junit.*;


import javax.persistence.*;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/08
 * Feature:
 */
public class EntryExitTest
{

    private AccessEvent access;
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EntityTransaction entityTransaction;


    @Test
    @Before
    public void createAccessEventTest() throws Exception
    {
    	System.out.println("Running createAccessEventTest...");
        access = new AccessEvent("tashan.avi@gmail.com", "u12345678", "ComboSmart", "Avinash", "Singh", "2007-09-23 10:10:10.0", (long) 0);
        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();

// JUST BEING USED SO THE EXACT SAME EVENT DOESNT GET LOGGED EVERY TIME THE TEST IS RUN.
        Query query = entityManager.createQuery("SELECT a FROM AccessEvent a WHERE email='tashan.avi@gmail.com' AND timestamp='2007-09-23 10:10:10.0'");
        List<AccessEvent> result = query.getResultList();

        if (result.size() == 0)
        {
            entityTransaction.begin();
            entityManager.persist(access);
            entityTransaction.commit();
        }
////////////////////////////////////////////////////////////////////////////////

     //    query = entityManager.createQuery("SELECT a FROM AccessEvent a WHERE email='tashan.avi@gmail.com' AND timestamp='2007-09-23 10:10:10.0'");
     //    List<AccessEvent> res = query.getResultList();
        
     //    if(res.size() > 0)
     //    {
	    //     Assert.assertEquals("Incorrect email address", "tashan.avi@gmail.com", res.get(0).getEmail());
	    //     Assert.assertEquals("Incorrect timestamp", "2007-09-23 10:10:10.0", res.get(0).getTimestamp());
	    // }
        // if(result.size() == 1)
        // {

        // }

    }

    @Test
    public void pojoTest() throws Exception
    {
        Assert.assertEquals("Not the same name", "Avinash", access.getName());
        Assert.assertEquals("Not the same email", "tashan.avi@gmail.com", access.getEmail());
        Assert.assertEquals("Not the same surname", "Singh", access.getSurname());
        Assert.assertEquals("Not the same userID", "u12345678", access.getEmployeeID());
        Assert.assertEquals("Not the same deviceID", "ComboSmart", access.getDeviceID());
        Assert.assertEquals("Not the same day", new Long(0), access.getDay());
        Assert.assertEquals("Not the same timestamp", "2007-09-23 10:10:10.0", access.getTimestamp());


    }


    @Test
//    @Ignore
    public void queryTest() throws Exception
    {
        // query and test

        System.out.println("Running QueryTest ...");

        Query query = entityManager.createQuery("SELECT a FROM AccessEvent a WHERE (email='tashan.avi@gmail.com')");
        List<AccessEvent> result = query.getResultList();
        Assert.assertNotEquals(result.size(), 0);
        Assert.assertEquals("Not the same name", result.get(0).getName(), access.getName());
        Assert.assertEquals("Not the same email", result.get(0).getEmail(), access.getEmail());

        Assert.assertEquals("Not the same surname", result.get(0).getSurname(), access.getSurname());
        Assert.assertEquals("Not the same userID", result.get(0).getEmployeeID(), access.getEmployeeID());
        Assert.assertEquals("Not the same deviceID", result.get(0).getDeviceID(), access.getDeviceID());
        Assert.assertEquals("Not the same day", result.get(0).getDay(), access.getDay());
        Assert.assertEquals("Not the same timestamp", result.get(0).getTimestamp(), access.getTimestamp());

    }




    @After
    public void closeManager()
    {
        entityManager.close();
        entityManagerFactory.close();
    }



}
