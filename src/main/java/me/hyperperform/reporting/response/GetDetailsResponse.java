package me.hyperperform.reporting.response;

import me.hyperperform.reporting.TravisDetails;

/**
 * Created by rohan on 2016/08/23.
 */
public class GetDetailsResponse {

    /*----------All possible detail types----------*/
    private TravisDetails travisDetails;
    /*---------------------------------------------*/

    public TravisDetails getTravisDetails() {
        return travisDetails;
    }

    public void setTravisDetails(TravisDetails travisDetails) {
        this.travisDetails = travisDetails;
    }
}
