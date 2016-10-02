package me.hyperperform.forecasting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by rohan on 2016/10/02.
 */

@Entity
@Table(name = "\"ForecastData\"")
public class ForecastData
{
    @Id
    @Column(name = "data", length = 10485760)
    private String data;

    public ForecastData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
