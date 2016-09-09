package me.hyperperform.user.response;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/09
 * Feature:
 */
public class VerifyLoginResponse
{

    private Boolean isLoggedin = null;

    public VerifyLoginResponse(Boolean isLoggedin)
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
