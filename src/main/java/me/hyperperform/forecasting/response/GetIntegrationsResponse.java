package me.hyperperform.forecasting.response;

/**
 * Created by rohan on 2016/10/02.
 */
public class GetIntegrationsResponse
{
    private int size;
    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
