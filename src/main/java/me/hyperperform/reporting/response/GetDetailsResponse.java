package me.hyperperform.reporting.response;

import me.hyperperform.reporting.GitDetails;
import me.hyperperform.reporting.TravisDetails;

/**
 * Created by rohan on 2016/08/23.
 */
public class GetDetailsResponse {

    /*----------All possible detail types----------*/
    private TravisDetails travisDetails;
    private GitDetails gitDetails;
    /*---------------------------------------------*/

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
}
