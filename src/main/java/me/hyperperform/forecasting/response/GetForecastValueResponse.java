package me.hyperperform.forecasting.response;

/**
 * A POJO for the response to be sent back to the caller after the respective request {@see me.hyperperform.forecasting.request.GetForecastValueRequest} has been made.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/10/05
 */
public class GetForecastValueResponse
{
    private double value;

    public GetForecastValueResponse(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
