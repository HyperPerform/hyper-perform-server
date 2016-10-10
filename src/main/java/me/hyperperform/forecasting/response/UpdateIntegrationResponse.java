package me.hyperperform.forecasting.response;

/**
 * A POJO for the response to be sent back to the caller after the respective request (@see me.hyperperform.forecasting.request.UpdateIntegrationRequest) and update of the database has been made.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/30
 */

public class UpdateIntegrationResponse
{
    private boolean updated;

    public UpdateIntegrationResponse() {
        this.updated = false;
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }
}
