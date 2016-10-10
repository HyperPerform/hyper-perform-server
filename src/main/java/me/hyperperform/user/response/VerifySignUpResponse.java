package me.hyperperform.user.response;


/**
 * Response object for verifySignup method found in {@see me.hyperperform.rest.LoginRest}.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/09
 */


public class VerifySignUpResponse
{
    private Boolean isLoggedin = null;

    public VerifySignUpResponse(Boolean isLoggedin)
    {
        this.isLoggedin = isLoggedin;
    }

    public Boolean getLoggedin()
    {
        return isLoggedin;
    }

    public void setLoggedin(Boolean loggedin)
    {
        isLoggedin = loggedin;
    }
}
