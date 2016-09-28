package me.hyperperform.reporting;

import me.hyperperform.event.EntryExit.AccessEvent;
import me.hyperperform.event.Git.GitIssue;
import me.hyperperform.event.Git.GitPush;
import me.hyperperform.event.Travis.TravisEvent;
import me.hyperperform.reporting.algorithm.Algorithm;
import me.hyperperform.reporting.algorithm.StandardAlgorithm;
import me.hyperperform.reporting.request.*;
import me.hyperperform.reporting.response.*;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by rohan on 2016/08/10.
 */

public class ReportGenerator implements IReport
{
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    private Algorithm algorithm;

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

    public ReportGenerator() {
    }

    public GetSummaryResponse getSummary(GetSummaryRequest getSummaryRequest)
    {
        GetSummaryResponse getSummaryResponse = new GetSummaryResponse();

        /*-------------------Mapping Email to name----------------------*/
        Query q = entityManager.createQuery("SELECT a.gitUserName FROM User a WHERE userEmail=:email").setParameter("email", getSummaryRequest.getName());
        getSummaryRequest.setName((String)q.getSingleResult());
        /*--------------------------------------------------------------*/

        /*---------------------------Github-----------------------------*/
        q = entityManager.createQuery("SELECT sum(a.commitSize) FROM GitPush a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (username=:uname)").setParameter("startDate", getSummaryRequest.getStartDate()).setParameter("endDate", getSummaryRequest.getEndDate()).setParameter("uname", getSummaryRequest.getName());

        Object totalCommits = q.getSingleResult();
        if (totalCommits != null)
            getSummaryResponse.setGithub((Long)totalCommits);
        /*--------------------------------------------------------------*/

        /*----------------------------Travis-----------------------------*/
        q = entityManager.createQuery("SELECT COUNT(a.status) FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname) AND (status LIKE 'Passed')").setParameter("startDate", getSummaryRequest.getStartDate()).setParameter("endDate", getSummaryRequest.getEndDate()).setParameter("uname", getSummaryRequest.getName());
        long passed = (Long)q.getSingleResult();

        q = entityManager.createQuery("SELECT COUNT(a.status) FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname) AND (status LIKE 'Failed')").setParameter("startDate", getSummaryRequest.getStartDate()).setParameter("endDate", getSummaryRequest.getEndDate()).setParameter("uname", getSummaryRequest.getName());
        long failed = (Long)q.getSingleResult();

        double successRate = ((double)passed/(double)(passed+failed)) * 100.0;
        int roundTmp = (int)(successRate*100.0);
        successRate = roundTmp/100.0;

        getSummaryResponse.setTravis(successRate);
        /*--------------------------------------------------------------*/

        /*--------------------------Bug Tracking------------------------*/
        q = entityManager.createQuery("SELECT COUNT(a.action) FROM GitIssue a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (assignee=:uname) AND (action LIKE 'assigned')").setParameter("startDate", getSummaryRequest.getStartDate()).setParameter("endDate", getSummaryRequest.getEndDate()).setParameter("uname", getSummaryRequest.getName());
        long assigned = (Long)q.getSingleResult();

        q = entityManager.createQuery("SELECT COUNT(a.action) FROM GitIssue a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (assignee=:uname) AND (action LIKE 'closed')").setParameter("startDate", getSummaryRequest.getStartDate()).setParameter("endDate", getSummaryRequest.getEndDate()).setParameter("uname", getSummaryRequest.getName());
        long closed = (Long)q.getSingleResult();

        double closeRate = ((double)closed/(double)assigned) * 100.0;
        int roundTmp2 = (int)(closeRate*100.0);
        closeRate = roundTmp2/100.0;

        getSummaryResponse.setIssues(closeRate);
        /*--------------------------------------------------------------*/

        /*--------------------------Entry Exit------------------------*/
        q = entityManager.createQuery("SELECT COUNT(*) FROM AccessEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (employeeID=:uname)").setParameter("startDate", getSummaryRequest.getStartDate()).setParameter("endDate", getSummaryRequest.getEndDate()).setParameter("uname", getSummaryRequest.getName());
        long loghours = (Long)q.getSingleResult();


        //@TODO Get management forecast and update

        getSummaryResponse.setEntryExit(loghours);
        /*--------------------------------------------------------------*/

        return getSummaryResponse;
    }

    public GetDetailsResponse getDetails(GetDetailsRequest getDetailsRequest) {

        /*-------------------Mapping Email to name----------------------*/
        Query z = entityManager.createQuery("SELECT a.gitUserName FROM User a WHERE userEmail=:email").setParameter("email", getDetailsRequest.getName());
        getDetailsRequest.setName((String)z.getSingleResult());
        /*--------------------------------------------------------------*/

        GetDetailsResponse getDetailsResponse = new GetDetailsResponse();

        if (getDetailsRequest.getType().equals("travis"))
        {
            System.out.println("------------------------------------------------");
            System.out.println("Generating report for travis");
            System.out.println("------------------------------------------------");

            Query q = entityManager.createQuery("SELECT a FROM TravisEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (commiter=:uname)").setParameter("startDate", getDetailsRequest.getStartDate()).setParameter("endDate", getDetailsRequest.getEndDate()).setParameter("uname", getDetailsRequest.getName());
            List<TravisEvent> result = q.getResultList();

            ArrayList<String> repos = new ArrayList<String>();
            ArrayList<ArrayList<TravisEvent>> data = new ArrayList<ArrayList<TravisEvent>>();

            for (int k = 0; k < result.size(); k++)
            {
                TravisEvent curr = result.get(k);

                if (repos.indexOf(curr.getRepo()) == -1)
                {
                    repos.add(curr.getRepo());
                    data.add(new ArrayList<TravisEvent>());
                }

                data.get(repos.indexOf(curr.getRepo())).add(curr);
            }

            getDetailsResponse.setTravisDetails(new TravisDetails(data.size(), data));
        }

        else if (getDetailsRequest.getType().equals("git"))
        {
            Query q = entityManager.createQuery("SELECT a FROM GitPush a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (username=:uname)").setParameter("startDate", getDetailsRequest.getStartDate()).setParameter("endDate", getDetailsRequest.getEndDate()).setParameter("uname", getDetailsRequest.getName());
            List<GitPush> result = q.getResultList();

            ArrayList<String> repos = new ArrayList<String>();
            ArrayList<ArrayList<GitPush>> data = new ArrayList<ArrayList<GitPush>>();

            for (int k = 0; k < result.size(); k++)
            {
                GitPush curr = result.get(k);

                if (repos.indexOf(curr.getRepository()) == -1)
                {
                    repos.add(curr.getRepository());
                    data.add(new ArrayList<GitPush>());
                }

                data.get(repos.indexOf(curr.getRepository())).add(curr);
            }



            long range = (getDetailsRequest.getEndDate().getTime() - getDetailsRequest.getStartDate().getTime());
            range /= 10;

            ArrayList<GraphData<String, Long>> graphData = new ArrayList<GraphData<String, Long>>();

            for (int k = 0; k < repos.size(); k++)
            {
                ArrayList<String> xAxis = new ArrayList<String>();
                ArrayList<Long> yAxis = new ArrayList<Long>();

                long prev = getDetailsRequest.getStartDate().getTime();
                for (int j = 1; j <= 10; j++)
                {
                    long curr = prev + range;

                    Query dataQuery = entityManager.createQuery("SELECT SUM(a.commitSize) FROM GitPush a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (username=:uname) AND (repository=:repo)")
                            .setParameter("startDate", new Timestamp(prev))
                            .setParameter("endDate", new Timestamp(curr))
                            .setParameter("uname", getDetailsRequest.getName())
                            .setParameter("repo", repos.get(k));

                    Object commits = dataQuery.getSingleResult();

                    String currXLabel = (new Timestamp(curr)).toString();

                    xAxis.add(currXLabel.substring(0, currXLabel.indexOf(" ")));
                    yAxis.add((commits == null) ? 0 : (Long) commits);

                    prev = curr;
                }

                graphData.add(new GraphData<String, Long>(xAxis, yAxis));
            }

            getDetailsResponse.setGitDetails(new GitDetails(data.size(), data, graphData));

        }
        else
        if (getDetailsRequest.getType().equals("issues"))
        {
            Query q = entityManager.createQuery("SELECT a FROM GitIssue a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (assignee=:uname) AND (action='closed' OR action='assigned')").setParameter("startDate", getDetailsRequest.getStartDate()).setParameter("endDate", getDetailsRequest.getEndDate()).setParameter("uname", getDetailsRequest.getName());
            List<GitIssue> result = q.getResultList();

            ArrayList<String> repos = new ArrayList<String>();
            ArrayList<ArrayList<GitIssue>> data = new ArrayList<ArrayList<GitIssue>>();

            for (int k = 0; k < result.size(); k++)
            {
                GitIssue curr = result.get(k);

                if (repos.indexOf(curr.getRepository()) == -1)
                {
                    repos.add(curr.getRepository());
                    data.add(new ArrayList<GitIssue>());
                }

                data.get(repos.indexOf(curr.getRepository())).add(curr);
            }

            getDetailsResponse.setGitIssueDetails(new GitIssueDetails(data.size(), data));
        }
        else
        if (getDetailsRequest.getType().equals("entry"))
        {
            Query q = entityManager.createQuery("SELECT a FROM AccessEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (name=:uname) order by timestamp").setParameter("startDate", getDetailsRequest.getStartDate()).setParameter("endDate", getDetailsRequest.getEndDate()).setParameter("uname", getDetailsRequest.getName());
            List<AccessEvent> result = q.getResultList();

            getDetailsResponse.setAccessDetails(new AccessDetails((ArrayList<AccessEvent>) result));

            long range = (getDetailsRequest.getEndDate().getTime() - getDetailsRequest.getStartDate().getTime());
            range /= 10;

            GraphData<String, Long> graphData = new GraphData<String, Long>();

            ArrayList<String> xAxis = new ArrayList<String>();
            ArrayList<Long> yAxis = new ArrayList<Long>();

            long prev = getDetailsRequest.getStartDate().getTime();
            for (int j = 1; j <= 10; j++)
            {
                long curr = prev + range;

                Query dataQuery = entityManager.createQuery("SELECT a FROM AccessEvent a WHERE (timestamp BETWEEN :startDate AND :endDate) AND (name=:uname) order by timestamp")
                        .setParameter("startDate", new Timestamp(prev))
                        .setParameter("endDate", new Timestamp(curr))
                        .setParameter("uname", getDetailsRequest.getName());

                String currXLabel = (new Timestamp(curr)).toString();

                List<AccessEvent> list = dataQuery.getResultList();

                long val = 0;
                for (int k = 0; k < list.size(); k += 2)
                {
                    Timestamp a = Timestamp.valueOf(list.get(k).getTimestamp());
                    Timestamp b = Timestamp.valueOf(list.get((k+1 < list.size()) ? k+1 : k ).getTimestamp());

                    val += TimeUnit.MILLISECONDS.toHours(b.getTime() - a.getTime());
                }

                xAxis.add(currXLabel.substring(0, currXLabel.indexOf(" ")));
                yAxis.add(val);

                prev = curr;
            }

            graphData.setIndependent(xAxis);
            graphData.setDependent(yAxis);
        }

        return getDetailsResponse;
    }

    public GetScoreResponse getScore(GetScoreRequest getScoreRequest)
    {
        /*-------------------Mapping Email to name----------------------*/
        Query q = entityManager.createQuery("SELECT a.gitUserName FROM User a WHERE userEmail=:email").setParameter("email", getScoreRequest.getName());
        getScoreRequest.setName((String)q.getSingleResult());
        /*--------------------------------------------------------------*/

        CalculateScoreRequest calculateScoreRequest = new CalculateScoreRequest();
        calculateScoreRequest.setName(getScoreRequest.getName());
        calculateScoreRequest.setStartDate(getScoreRequest.getStartDate());
        calculateScoreRequest.setEndDate(getScoreRequest.getEndDate());

        algorithm = new StandardAlgorithm();

        CalculateScoreResponse calculateScoreResponse = algorithm.calculateScore(calculateScoreRequest);

        String performance = "Non-performer";

        double score = calculateScoreResponse.getScore();
        if (score >= 2.0 && score < 3.0)
            performance = "Standard performer";
        else
        if (score >= 3.0 && score < 4.0)
            performance = "Standard plus performer";
        else
        if (score >= 4.0)
            performance = "High performer";

        return new GetScoreResponse(score, performance);
    }
}
