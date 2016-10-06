package me.hyperperform.forecasting.response;

/**
 * Created by rohan on 2016/10/06.
 */
public class GetForecastTimeResponse
{
    private String time;

    public GetForecastTimeResponse(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
