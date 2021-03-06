package me.hyperperform.user.response;

import me.hyperperform.user.Position;

/**
 * Response object for verifyLogin method found in {@see me.hyperperform.rest.LoginRest}.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/09
 */

public class VerifyLoginResponse
{

    private Boolean isLoggedin = null;
    private String email = null;
    private String name = null;
    private Position position = null;

    public VerifyLoginResponse(Boolean isLoggedin, String email, String name, Position position)
    {
        this.isLoggedin = isLoggedin;
        this.email = email;
        this.name = name;
        this.position = position;
    }

    public Boolean getLoggedin()
    {
        return isLoggedin;
    }

    public void setLoggedin(Boolean loggedin)
    {
        isLoggedin = loggedin;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Position getPosition()
    {
        return position;
    }

    public void setPosition(Position position)
    {
        this.position = position;
    }
}
