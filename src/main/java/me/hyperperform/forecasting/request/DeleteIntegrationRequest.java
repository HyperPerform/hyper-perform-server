package me.hyperperform.forecasting.request;

/**
 * A POJO for the request data coming from the caller for an integration to be deleted from the json string.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/10/01
 */
public class DeleteIntegrationRequest
{
    private String integrationName;

    public DeleteIntegrationRequest() {
    }

    public DeleteIntegrationRequest(String integrationName) {
        this.integrationName = integrationName;
    }

    public String getIntegrationName() {
        return integrationName;
    }

    public void setIntegrationName(String integrationName) {
        this.integrationName = integrationName;
    }
}
