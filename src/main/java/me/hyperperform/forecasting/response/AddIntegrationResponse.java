package me.hyperperform.forecasting.response;

/**
 * A POJO for the response to be sent back to the caller after the respective request (@see me.hyperperform.forecasting.request.AddIntegrationRequest) and addition to the database has been made.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/10/05
 */

public class AddIntegrationResponse
{
    private boolean added;

    public AddIntegrationResponse() {
    }

    public boolean isAdded() {
        return added;
    }

    public void setAdded(boolean added) {
        this.added = added;
    }
}
