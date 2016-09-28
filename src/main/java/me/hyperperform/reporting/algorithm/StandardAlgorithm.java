package me.hyperperform.reporting.algorithm;

import me.hyperperform.reporting.request.CalculateScoreRequest;
import me.hyperperform.reporting.response.CalculateScoreResponse;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A concrete realization of the {@see me.hyperperform.reporting.algorithm.Algorithm} interface. This class serves as
 * the ConcreteStrategy in the strategy design pattern.
 *
 * Created by rohan on 2016/08/19.
 */
public class StandardAlgorithm implements Algorithm
{
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    /**
     * Implementation of the calculateScore found in {@see me.hyperperform.reporting.algorithm.Algorithm}.
     *
     * @param calculateScoreRequest Contains the necessary parameters for score calculations which include the user for
     *                              whom the score is being calculated as well as a time period.
     * @return A {@see me.hyperperform.reporting.response.CalculateScoreResponse} object is returned. This object
     * the calculated score.
     */
    public CalculateScoreResponse calculateScore(CalculateScoreRequest calculateScoreRequest)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();



        long numOfDays = TimeUnit.DAYS.convert(calculateScoreRequest.getEndDate().getTime() - calculateScoreRequest.getStartDate().getTime(), TimeUnit.MILLISECONDS);

        /*---------------------------------------------------------------------*/
        Query q = entityManager.createQuery("SELECT sum(a.commitSize) FROM GitPush a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (username=:uname)").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", calculateScoreRequest.getName());

        Long tmp = (Long)q.getSingleResult();
        long sumCommits = (tmp == null) ? 0 : tmp;
        /*---------------------------------------------------------------------*/

        /*---------------------------------------------------------------------*/
        q = entityManager.createQuery("SELECT COUNT(a.status) FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname) AND (status LIKE 'Passed')").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", calculateScoreRequest.getName());
        tmp = (Long)q.getSingleResult();
        long passed = (tmp == null) ? 0 : tmp;

        q = entityManager.createQuery("SELECT COUNT(a.status) FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname) AND (status LIKE 'Failed')").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", calculateScoreRequest.getName());
        tmp = (Long)q.getSingleResult();
        long failed = (tmp == null) ? 1 : tmp;
        /*---------------------------------------------------------------------*/

        double score = (0.5*(sumCommits/(5.0*numOfDays))) + (0.5*((double)passed/(passed+failed)));

        score = scale(score, 0.0, 5.0);

        entityManager.close();
        entityManagerFactory.close();

        return new CalculateScoreResponse((Double.isNaN(score)) ? 0.0 : score);
    }

    private double scale(double value, double start, double end)
    {
        return (value*(end-start)) + start;
    }
}
