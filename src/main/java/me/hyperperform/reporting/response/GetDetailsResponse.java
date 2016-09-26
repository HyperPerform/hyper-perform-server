package me.hyperperform.reporting.response;

import me.hyperperform.event.Git.GitIssue;
import me.hyperperform.reporting.AccessDetails;
import me.hyperperform.reporting.GitDetails;
import me.hyperperform.reporting.GitIssueDetails;
import me.hyperperform.reporting.TravisDetails;

/**
 * Created by rohan on 2016/08/23.
 */
public class GetDetailsResponse {

    /*----------All possible detail types----------*/
    private TravisDetails travisDetails;
    private GitDetails gitDetails;
    private GitIssueDetails gitIssueDetails;
    private AccessDetails accessDetails;
    /*---------------------------------------------*/

    public AccessDetails getAccessDetails()
    {
        return accessDetails;
    }

    public void setAccessDetails(AccessDetails accessDetails)
    {
        this.accessDetails = accessDetails;
    }
    public TravisDetails getTravisDetails() {
        return travisDetails;
    }
    public GitDetails getGitDetails() {
        return gitDetails;
    }

    public void setTravisDetails(TravisDetails travisDetails) {
        this.travisDetails = travisDetails;
    }
    public void setGitDetails(GitDetails gitDetails) {
        this.gitDetails = gitDetails;
    }

    public GitIssueDetails getGitIssueDetails() {
        return gitIssueDetails;
    }

    public void setGitIssueDetails(GitIssueDetails gitIssueDetails) {
        this.gitIssueDetails = gitIssueDetails;
    }
}
