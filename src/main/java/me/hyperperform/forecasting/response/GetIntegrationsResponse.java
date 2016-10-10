package me.hyperperform.forecasting.response;

/**
 * A POJO for the response to be sent back to the caller after the respective request {@see me.hyperperform.forecasting.request.GetIntegrationsRequest} has been made.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/29
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
