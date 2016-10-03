package me.hyperperform.forecasting.request;

public class UpdateIntegrationRequest
{
    private String data;

    public UpdateIntegrationRequest() {
    }

    public UpdateIntegrationRequest(String data) {
        this.data = data;
    }

    public String getData()
    {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
