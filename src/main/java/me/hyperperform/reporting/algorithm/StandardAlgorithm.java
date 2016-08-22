package me.hyperperform.reporting.algorithm;

import me.hyperperform.reporting.request.CalculateScoreRequest;
import me.hyperperform.reporting.response.CalculateScoreResponse;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.concurrent.TimeUnit;

/**
 * Created by rohan on 2016/08/19.
 */
public class StandardAlgorithm implements Algorithm
{
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;

    @PostConstruct
    private void initConnection()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
    }

    @PreDestroy
    private void disconnect()
    {
        entityManager.close();
        entityManagerFactory.close();
    }

    public CalculateScoreResponse calculateScore(CalculateScoreRequest calculateScoreRequest)
    {
        long numOfDays = TimeUnit.DAYS.convert(calculateScoreRequest.getEndDate().getTime() - calculateScoreRequest.getStartDate().getTime(), TimeUnit.MILLISECONDS);

        /*---------------------------------------------------------------------*/
        Query q = entityManager.createQuery("SELECT sum(a.commitSize) FROM GitPush a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (username=:uname)").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", calculateScoreRequest.getName());
        long sumCommits = (Long)q.getSingleResult();
        /*---------------------------------------------------------------------*/

        /*---------------------------------------------------------------------*/
        q = entityManager.createQuery("SELECT COUNT(a.status) FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname) AND (status LIKE 'Passed')").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", calculateScoreRequest.getName());
        long passed = (Long)q.getSingleResult();

        q = entityManager.createQuery("SELECT COUNT(a.status) FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname) AND (status LIKE 'Failed')").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", calculateScoreRequest.getName());
        long failed = (Long)q.getSingleResult();
        /*---------------------------------------------------------------------*/

        double score = (0.5*(sumCommits/(5.0*numOfDays))) + (0.5*((double)passed/(passed+failed)));

        return null;
    }
}
