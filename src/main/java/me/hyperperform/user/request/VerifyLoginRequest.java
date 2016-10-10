package me.hyperperform.user.request;

/**
 * Request object for verifyLogin method found in {@see me.hyperperform.rest.LoginRest}.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/09
 */

public class VerifyLoginRequest
{
    private String userEmail;
    private String userPassword;

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }
}
