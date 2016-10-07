package me.hyperperform.user.response;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/10/07
 * Feature:
 */
public class ForgotPasswordResponse
{
    String result;

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
