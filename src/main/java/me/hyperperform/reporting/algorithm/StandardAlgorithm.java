package me.hyperperform.reporting.algorithm;

import me.hyperperform.forecasting.Forecasting;
import me.hyperperform.forecasting.IForecasting;
import me.hyperperform.forecasting.request.GetForecastTimeRequest;
import me.hyperperform.forecasting.request.GetForecastValueRequest;
import me.hyperperform.reporting.request.CalculateScoreRequest;
import me.hyperperform.reporting.response.CalculateScoreResponse;
import me.hyperperform.user.Position;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import javax.inject.Inject;
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

    @Inject
    IForecasting forecasting;

    @PostConstruct
    private void initConnection() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PostgreJPA");
        entityManager = entityManagerFactory.createEntityManager();
    }



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
        /*-------------------Mapping Email to name----------------------*/
        Query q = entityManager.createQuery("SELECT a.gitUserName FROM User a WHERE userEmail=:email").setParameter("email", calculateScoreRequest.getName());
        String gitUserName = (String)q.getSingleResult();
        //*--------------------------------------------------------------*

//        GetForecastTimeRequest getForecastTimeRequest = new GetForecastTimeRequest("GitCommits", getPosition(calculateScoreRequest.getName()));
        System.out.println("======================================");
        System.out.println(forecasting);
        System.out.println("======================================");

// long timeGit = convertDays(0, forecasting.getForecastTime(getForecastTimeRequest).getTime());

//        long numOfDays = TimeUnit.DAYS.convert(calculateScoreRequest.getEndDate().getTime() - calculateScoreRequest.getStartDate().getTime(), TimeUnit.MILLISECONDS);
//        long time = TimeUnit.MILLISECONDS.toDays(calculateScoreRequest.getEndDate().getTime() - calculateScoreRequest.getStartDate().getTime());
//
//        /*----------------------   GitHub   -----------------------------------*/
//        q = entityManager.createQuery("SELECT sum(a.commitSize) FROM GitPush a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (username=:uname)").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", gitUserName);
//
//        Long tmp = (Long)q.getSingleResult();
//        long totalCommits = (tmp == null) ? 0 : tmp;
//
//        GetForecastTimeRequest getForecastTimeRequest = new GetForecastTimeRequest("GitCommits", getPosition(calculateScoreRequest.getName()));
//        long timeGit = convertDays(time, forecasting.getForecastTime(getForecastTimeRequest).getTime());
//
//        GetForecastValueRequest getForecastValueRequest = new GetForecastValueRequest("GitCommits", getPosition(calculateScoreRequest.getName()));
//        double forecastValue = forecasting.getForecastValue(getForecastValueRequest).getValue();
//
//        double avg = (double) totalCommits / (double) timeGit;
//        avg /= forecastValue;
//        tmp = (long) (avg * 10000.0);
//
//        double git = (double) (tmp) / 100.0;
//        System.out.println("\n\nGit: " + git );
//        /*---------------------------------------------------------------------*/
//
//
//
//        /*----------------------   Travis   -----------------------------------*/
//        q = entityManager.createQuery("SELECT COUNT(a.status) FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname) AND (status LIKE 'Passed')").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", gitUserName);
//        tmp = (Long)q.getSingleResult();
//        long passed = (tmp == null) ? 0 : tmp;
//
//        q = entityManager.createQuery("SELECT COUNT(a.status) FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname) AND (status LIKE 'Failed')").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", gitUserName);
//        tmp = (Long)q.getSingleResult();
//        long failed = (tmp == null) ? 1 : tmp;
//
//
//        getForecastTimeRequest = new GetForecastTimeRequest("TravisBuild", getPosition(calculateScoreRequest.getName()));
//        long timeTravis = convertDays(time, forecasting.getForecastTime(getForecastTimeRequest).getTime());
//
//        getForecastValueRequest = new GetForecastValueRequest("TravisBuild", getPosition(calculateScoreRequest.getName()));
//        forecastValue = forecasting.getForecastValue(getForecastValueRequest).getValue();
//
//        avg = (double) passed / (double)(passed+failed);
//        avg *= 100.0;
//
//        double travis = avg - forecastValue;
////        avg = (double) passed / (double) timeTravis;
////        avg /= forecastValue;
////        tmp = (long) (avg * 10000.0);
////
////        double travis = (double) (tmp) / 100.0;
//        System.out.println("\n\nTravis: " + travis );
//        /*---------------------------------------------------------------------*/
//
//
//        /*----------------------   Issues   -----------------------------------*/
//        double issues = 0.0;
//        /*---------------------------------------------------------------------*/
//
//        /*----------------------   Entry   -----------------------------------*/
//         double entry = 0.0;
//        /*---------------------------------------------------------------------*/
//
//
//
//        /*---------------------------------------------------------------------*/
//        /*-------------------      Score  Generation    -----------------------*/
//        /*---------------------------------------------------------------------*/
//        double score = (0.4*(git)) + (0.2*(travis) + (0.2*(issues)) + (0.2*(entry)));
//
//        score = scale(score, 0.0, 5.0);
//
//        return new CalculateScoreResponse((Double.isNaN(score)) ? 0.0 : score);

        return new CalculateScoreResponse(0.0);
    }


    private String getPosition(String user) {
        Query q = entityManager.createQuery("select position from User where userEmail=:email").setParameter("email", user);
        Position p = (Position) q.getSingleResult();

        return (p == null) ? null : p.getType();
    }

    private long convertDays(long days, String time)
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
    private double scale(double value, double start, double end)
    {
        return (value*(end-start)) + start;
    }
}
