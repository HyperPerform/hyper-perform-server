package me.hyperperform.forecasting.request;

/**
 * A POJO for the request data coming from the caller, for an integration to be added to the json string.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/10/01
 */
public class AddIntegrationRequest
{
    private String data;

    public AddIntegrationRequest() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
