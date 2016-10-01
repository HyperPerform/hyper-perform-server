package me.hyperperform.user;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class DatabasePopulatorUsersTest
{
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    EntityTransaction entityTransaction;

    @Before
    public void initConnection()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    @After
    public void disconnect()
    {
        entityManager.close();
        entityManagerFactory.close();
    }

//    @Ignore
    @Test
    public void populateTest()
    {
        System.out.println("Adding mock users to database...");
        ArrayList<User> users = new ArrayList<User>();
        users.add(new User("admin@hyperperform.me", "Admin", "admin", "admin", "admin", "1234", null, EmployeeRole.Administrator, Position.Manager));
        users.add(new User("rohan.chhipa@live.com", "Rohan", "rohanchhipa", "Rohan", "Chhipa", "1234", null, EmployeeRole.Employee, Position.SoftwareDeveloper));
        users.add(new User("avinash.singh@gmail.com", "Avinash", "avinashsingh786", "Avinash", "Singh", "1234", null, EmployeeRole.Employee, Position.SoftwareDeveloper));
        users.add(new User("jason.gordon@gmail.com", "Jason", "jasongordon", "Jason", "Gordon", "1234", null, EmployeeRole.Employee, Position.SoftwareDeveloper));
        users.add(new User("claudio.da.silva@live.com", "Claudio", "claudiodasilva", "Claudio", "da Silva", "1234", null, EmployeeRole.Employee, Position.Multimedia));

        entityTransaction.begin();
        entityManager.createQuery("DELETE FROM User").executeUpdate();
        entityTransaction.commit();

//        Query query = entityManager.createQuery("FROM User", User.class);
//        List<User> result = query.getResultList();

        entityTransaction.begin();
        for (int k = 0; k < users.size(); k++)
        {
//            boolean flag = true;
//
//            for (int j = 0; j < users.size(); j++)
//            for (int i = 0; i < result.size(); i++)
//                if (result.get(i).getUserEmail().equals(users.get(j).getUserEmail()))
//                    flag = false;
//            if (flag)
            entityManager.persist(users.get(k));
        }
        entityTransaction.commit();
    }
}
