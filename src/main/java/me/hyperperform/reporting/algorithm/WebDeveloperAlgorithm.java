package me.hyperperform.reporting.algorithm;

import me.hyperperform.event.EntryExit.AccessEvent;
import me.hyperperform.forecasting.Forecasting;
import me.hyperperform.forecasting.IForecasting;
import me.hyperperform.forecasting.request.GetForecastTimeRequest;
import me.hyperperform.forecasting.request.GetForecastValueRequest;
import me.hyperperform.reporting.request.CalculateScoreRequest;
import me.hyperperform.reporting.response.CalculateScoreResponse;
import me.hyperperform.user.Position;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * A concrete realization of the {@see me.hyperperform.reporting.algorithm.Algorithm} interface. This class serves as
 * a ConcreteStrategy in the strategy design pattern. This is used for the calculation of the score for a Web developer position scaling * it with regard to the forecasts given.
 * @author : CodusMaximus
 * @version : 1.0
 * @since : 2016/10/10
 */
public class WebDeveloperAlgorithm implements Algorithm
{
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    IForecasting forecasting;

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
        forecasting = new Forecasting();

        /*-------------------Mapping Email to name----------------------*/
        Query q = entityManager.createQuery("SELECT a.gitUserName FROM User a WHERE userEmail=:email").setParameter("email", calculateScoreRequest.getName());
        String gitUserName = (String)q.getSingleResult();
        //*--------------------------------------------------------------*

        long numOfDays = TimeUnit.DAYS.convert(calculateScoreRequest.getEndDate().getTime() - calculateScoreRequest.getStartDate().getTime(), TimeUnit.MILLISECONDS);
        long time = TimeUnit.MILLISECONDS.toDays(calculateScoreRequest.getEndDate().getTime() - calculateScoreRequest.getStartDate().getTime());

        /*----------------------   GitHub   -----------------------------------*/
        q = entityManager.createQuery("SELECT sum(a.commitSize) FROM GitPush a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (username=:uname)").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", gitUserName);

        Long tmp = (Long)q.getSingleResult();
        long totalCommits = (tmp == null) ? 0 : tmp;

        GetForecastTimeRequest getForecastTimeRequest = new GetForecastTimeRequest("GitCommits", Position.WebDeveloper.getType());
        long timeGit = Utility.convertDays(time, forecasting.getForecastTime(getForecastTimeRequest).getTime());

        GetForecastValueRequest getForecastValueRequest = new GetForecastValueRequest("GitCommits", Position.WebDeveloper.getType());
        double forecastValue = forecasting.getForecastValue(getForecastValueRequest).getValue();

        double avg = 0;
        double git = 0;

        if (forecastValue > 0) {
            avg = (double) totalCommits / (double) timeGit;
            git = avg / forecastValue;
        }

        System.out.println("\n\nGit: " + git + " forecasted: " + forecastValue + " average: " + avg);
        /*---------------------------------------------------------------------*/



        /*----------------------   Travis   -----------------------------------*/
        q = entityManager.createQuery("SELECT COUNT(a.status) FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname) AND (status LIKE 'Passed')").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", gitUserName);
        tmp = (Long)q.getSingleResult();
        long passed = (tmp == null) ? 0 : tmp;

        q = entityManager.createQuery("SELECT COUNT(a.status) FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname) AND (status LIKE 'Failed')").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", gitUserName);
        tmp = (Long)q.getSingleResult();
        long failed = (tmp == null) ? 1 : tmp;

        getForecastValueRequest.setIntegration("TravisBuild");
        forecastValue = forecasting.getForecastValue(getForecastValueRequest).getValue()/100.0;

        avg = (double) passed / (double)(passed+failed);

        double travis = 0;

        if (forecastValue > 0)
            travis = avg/forecastValue;

        System.out.println("\n\nTravis: " + travis + " forecasted: " + forecastValue + " Average: " + avg);
        /*---------------------------------------------------------------------*/

        /*----------------------   Issues   -----------------------------------*/
        double issues = 0.0;

        q = entityManager.createQuery("select count(*) from GitIssue where (timestamp between :startDate and :endDate) and (action like 'closed') and (createdBy = :uname or assignee = :uname)")
                .setParameter("startDate", calculateScoreRequest.getStartDate())
                .setParameter("endDate", calculateScoreRequest.getEndDate())
                .setParameter("uname", gitUserName);

        Long totalClosed = (Long)q.getSingleResult();
        totalClosed = (totalClosed == null) ? 0 : totalClosed;

        getForecastTimeRequest.setIntegration("GitIssues");
        double issuesTime = Utility.convertDays(time, forecasting.getForecastTime(getForecastTimeRequest).getTime());

        getForecastValueRequest.setIntegration("GitIssues");
        double issuesForecast = forecasting.getForecastValue(getForecastValueRequest).getValue();

        if (issuesForecast > 0) {
            issues = totalClosed / issuesTime;
            issues /= issuesForecast;
        }

        System.out.println("\n\n Issue value: " + issues + " forecast: " + issuesForecast + " average: " + totalClosed/issuesTime);
        /*---------------------------------------------------------------------*/

        /*----------------------   Entry   -----------------------------------*/
        double entry = 0.0;

        q = entityManager.createQuery("SELECT a FROM AccessEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (email=:uname) order by timestamp").setParameter("startDate", calculateScoreRequest.getStartDate()).setParameter("endDate", calculateScoreRequest.getEndDate()).setParameter("uname", calculateScoreRequest.getName());
        List<AccessEvent> list = (List<AccessEvent>) q.getResultList();

        double totalHours = 0.0;
        for (int k = 0; k < list.size(); k += 2)
        {
            Timestamp checkIn = Timestamp.valueOf(list.get(k).getTimestamp());
            Timestamp checkOut = Timestamp.valueOf(list.get(k+1).getTimestamp());

            totalHours += (double) TimeUnit.MILLISECONDS.toHours(checkOut.getTime() - checkIn.getTime());
        }

        getForecastTimeRequest.setIntegration("EntryExit");
        double entryExitTime = Utility.convertDays(time, forecasting.getForecastTime(getForecastTimeRequest).getTime());

        getForecastValueRequest.setIntegration("EntryExit");
        double entryExitForecast = forecasting.getForecastValue(getForecastValueRequest).getValue();

        if (entryExitForecast > 0) {
            entry = totalHours / entryExitTime;
            entry /= entryExitForecast;
        }

        System.out.println("\n\n Entry value: " + entry + " forecast: " + entryExitForecast + " average: " + totalHours/entryExitTime);
        /*---------------------------------------------------------------------*/


        /*---------------------------------------------------------------------*/
        /*-------------------      Score  Generation    -----------------------*/
        /*---------------------------------------------------------------------*/
//        double score = (0.4*(git)) + (0.2*(travis) + (0.2*(issues)) + (0.2*(entry)));
        double score = git + travis + issues + entry;

        score = Utility.scale(score, 4.0, 0.0, 5.0);

        entityManager.close();
        entityManagerFactory.close();

        return new CalculateScoreResponse((Double.isNaN(score)) ? 0.0 : score);

    }

}
