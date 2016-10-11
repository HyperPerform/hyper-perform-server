package me.hyperperform.reporting.algorithm;

import me.hyperperform.user.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Created by rohan on 2016/10/11.
 */
public class Utility
{
    public static double scale(double value, double total, double start, double end)
    {
        value /= total;
        value = (value*(end-start)) + start;

        return (value > end) ? end : value;
    }

    public String getPosition(String user) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query q = entityManager.createQuery("select position from User where userEmail=:email").setParameter("email", user);
        Position p = (Position) q.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return (p == null) ? null : p.getType();
    }

    public long convertDays(long days, String time)
    {
        System.out.println("D: " + days + "  " + time);
        if (time.equals("week"))
        {
            if (days != 0)
                return days/7;
        }

        if (time.equals("month"))
        {
            if (days != 0)
                return days/30;
        }

        return days;
    }
}
