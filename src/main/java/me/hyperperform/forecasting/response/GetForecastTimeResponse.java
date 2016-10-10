package me.hyperperform.forecasting.response;

/**
 * A POJO for the response to be sent back to the caller after the respective request {@see me.hyperperform.forecasting.request.GetForecastTimeRequest} has been made.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/10/05
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
