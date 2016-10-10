package me.hyperperform.reporting.response;

/**
 * Created by rohan on 2016/08/10.
 */
/**
 * Response object for GetSummary method found in {@see me.hyperperform.reporting.IReport}.
 *
 * @author : CodusMaximus
 * @version : 1.0
 * @since : 2016/08/10
 */
public class GetSummaryResponse {

    private double Github;
    private double travis;
    private double issues;
    private double entryExit;

    public double getGithub() {
        return Github;
    }

    public void setGithub(double github) {
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

    public double getEntryExit() {
        return entryExit;
    }

    public void setEntryExit(double entryExit) {
        this.entryExit = entryExit;
    }
}
