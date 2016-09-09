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
//    @Ignore
    public void createAccessEventTest() throws Exception
    {
        access = new AccessEvent("u12345678", "ComboSmart", "Avinash", "Singh", "2007-09-23 10:10:10.0", (long) 0);
        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();

        Query query = entityManager.createQuery("FROM AccessEvent ", AccessEvent.class);
        List<AccessEvent> result = query.getResultList();

        if (result.size() == 0)
        {
            entityTransaction.begin();
            entityManager.persist(access);
            entityTransaction.commit();
        }

    }

    @Test
//    @Ignore
    public void accessTest() throws Exception
    {
        Assert.assertEquals("Not the same name", "Avinash", access.getName());
        Assert.assertEquals("Not the same surname", "Singh", access.getSurname());
        Assert.assertEquals("Not the same userID", "u12345678", access.getEmployeeID());
        Assert.assertEquals("Not the same deviceID", "ComboSmart", access.getDeviceID());
        Assert.assertEquals("Not the same day", new Long(0), access.getDay());
        Assert.assertEquals("Not the same userID", "2007-09-23 10:10:10.0", access.getTimestamp().toString());


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
