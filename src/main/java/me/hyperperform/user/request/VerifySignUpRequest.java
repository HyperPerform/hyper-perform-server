package me.hyperperform.user.request;

import me.hyperperform.user.EmployeeRole;
import me.hyperperform.user.Position;



/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/09
 * Feature:
 */
public class VerifySignUpRequest
{
    private String userName;
    private String userSurname;
    private String userEmail;
    private String userPassword;
    private String role;
    private String position;

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserSurname()
    {
        return userSurname;
    }

    public void setUserSurname(String userSurname)
    {
        this.userSurname = userSurname;
    }

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

    public String getRole()
    {
        return role;
    }

    public void setRole(String role)
    {
        this.role = role;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public String toString()
    {
        String s = "";

        s += "Name: " + userName + "\n";
        s += "Surname: " + userSurname + "\n";
        s += "Email: " + userEmail + "\n";
        s += "Password: " + userPassword + "\n";
        s += "Role: " + role + "\n";
        s += "Position: " + position;

        return s;
    }
}
