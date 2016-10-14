package me.hyperperform.reporting.algorithm;

import me.hyperperform.user.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 * Utility class which contains helper functions for the algorithms
 *
 * @author : CodusMaximus
 * @version : 1.0
 * @since : 2016/10/11
 */
public class Utility
{
    /**
     * Scale function which can scale a given value, provided that the range is given.
     * @param value Value that needs to be scaled.
     * @param total The total possible value of the number to be scaled
     * @param start Starting of the range
     * @param end Ending of the range
     * @return A scaled value in between the given range
     */
    public static double scale(double value, double total, double start, double end)
    {
        value /= total;
        value = (value*(end-start)) + start;

        return (value > end) ? end : value;
    }

    /**
     * Helper function that allows the querying of a users position.
     * @param user The user whose role needs to be looked up.
     * @return Sring representation of the users position.
     */
    public static String getPosition(String user) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Query q = entityManager.createQuery("select position from User where userEmail=:email").setParameter("email", user);
        Position p = (Position) q.getSingleResult();

        entityManager.close();
        entityManagerFactory.close();

        return (p == null) ? null : p.getType();
    }

    /**
     * Converts days into a given time period. i.e weeks, months, etc.
     */
    public static long convertDays(long days, String time)
    {
        if (time == null)
            return days;

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
