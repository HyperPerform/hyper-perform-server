package me.hyperperform.forecasting.request;

/**
 * Created by rohan on 2016/10/02.
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
