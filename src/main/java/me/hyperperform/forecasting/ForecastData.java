package me.hyperperform.forecasting;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * A POJO that holds the forecasting data as a string.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/30
*/
@Entity
@Table(name = "\"ForecastData\"")
public class ForecastData
{
    @Id
    @Column(name = "data", length = 10485760)
    private String data;

    public ForecastData()
    {

    }

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
