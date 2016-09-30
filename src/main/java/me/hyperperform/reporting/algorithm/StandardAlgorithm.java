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
 * Created by rohan on 2016/08/19.
 */
public class StandardAlgorithm implements Algorithm
{
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public CalculateScoreResponse calculateScore(CalculateScoreRequest calculateScoreRequest)
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();

        /*-------------------Mapping Email to name----------------------*/
        Query q = entityManager.createQuery("SELECT a.gitUserName FROM User a WHERE userEmail=:email").setParameter("email", calculateScoreRequest.getName());
//        getScoreRequest.setName((String)q.getSingleResult());
        String gitUserName = (String)q.getSingleResult();
        //*--------------------------------------------------------------*


        long numOfDays = TimeUnit.DAYS.convert(calculateScoreRequest.getEndDate().getTime() - calculateScoreRequest.getStartDate().getTime(), TimeUnit.MILLISECONDS);

        /*---------------------------------------------------------------------*/
        q = entityManager.createQuery("SELECT sum(a.commitSize) FROM GitPush a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (username=:uname)").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", gitUserName);

        Long tmp = (Long)q.getSingleResult();
        long sumCommits = (tmp == null) ? 0 : tmp;
        /*---------------------------------------------------------------------*/

        /*---------------------------------------------------------------------*/
        q = entityManager.createQuery("SELECT COUNT(a.status) FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname) AND (status LIKE 'Passed')").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", gitUserName);
        tmp = (Long)q.getSingleResult();
        long passed = (tmp == null) ? 0 : tmp;

        q = entityManager.createQuery("SELECT COUNT(a.status) FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname) AND (status LIKE 'Failed')").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", gitUserName);
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
