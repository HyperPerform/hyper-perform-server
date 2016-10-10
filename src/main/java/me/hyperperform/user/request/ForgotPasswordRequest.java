package me.hyperperform.user.request;

/**
 * Request object for forgotPassword method found in {@see me.hyperperform.rest.LoginRest}.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/10/07
 */
public class ForgotPasswordRequest
{


    private String Email;

    public ForgotPasswordRequest()
    {

    }

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
