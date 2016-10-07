package me.hyperperform.user.request;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/10/07
 * Feature:
 */
public class ForgotPasswordRequest
{


    private String Email;

    public ForgotPasswordRequest(String email)
    {
        Email = email;
    }

    public String getEmail()
    {
        return Email;
    }

    public void setEmail(String email)
    {
        Email = email;
    }
}
