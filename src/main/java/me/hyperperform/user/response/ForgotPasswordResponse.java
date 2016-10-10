package me.hyperperform.user.response;

/**
 * Response object for forgotPassword method found in {@see me.hyperperform.rest.LoginRest}.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/10/07
 */
public class ForgotPasswordResponse
{
    String result;

    public ForgotPasswordResponse()
    {

    }
    public ForgotPasswordResponse(String result)
    {
        this.result = result;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }
}
