package me.hyperperform.forecasting.response;

/**
 * Created by rohan on 2016/10/06.
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
