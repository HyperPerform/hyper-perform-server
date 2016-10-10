package me.hyperperform.forecasting.response;

/**
 * A POJO for the response to be sent back to the caller after the respective request {@see me.hyperperform.forecasting.request.DeleteIntegrationRequest} and deletion from the database has been made.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/10/01
 */
public class DeleteIntegrationResponse
{
    private boolean deleted;

    public DeleteIntegrationResponse() {
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
