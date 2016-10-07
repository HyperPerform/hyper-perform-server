package me.hyperperform.forecasting.request;

/**
 * Created by rohan on 2016/10/06.
 */
public class GetForecastTimeRequest
{
    private String integration;
    private String position;

    public GetForecastTimeRequest() {
    }

    public GetForecastTimeRequest(String integration, String position) {
        this.integration = integration;
        this.position = position;
    }

    public String getIntegration() {
        return integration;
    }

    public void setIntegration(String integration) {
        this.integration = integration;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}