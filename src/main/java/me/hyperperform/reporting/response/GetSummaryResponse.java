package me.hyperperform.reporting.response;

/**
 * Created by rohan on 2016/08/10.
 */
public class GetSummaryResponse {

    private long Github;
    private double travis;
    private double issues;

    public long getGithub() {
        return Github;
    }

    public void setGithub(long github) {
        Github = github;
    }

    public double getTravis() {
        return travis;
    }

    public void setTravis(double travis) {
        this.travis = travis;
    }

    public double getIssues() {
        return issues;
    }

    public void setIssues(double issues) {
        this.issues = issues;
    }
}
