package me.hyperperform.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.awt.*;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/05
 * Feature:
 */

@Entity
@Table(name = "\"User\"")
public class User implements IPerson
{
    @Id
    private String userID = null;

    @Column(name = "Name")
    private String userName = null;

    @Column(name = "Surname")
    private String userSurname = null;

    @Column(name = "Email")
    private String userEmail = null;

    @Column(name = "Password")
    private String userPassword = null;



    //    @Column(name = "ProfilePicture")
//    private Image profilePicture = null;
    @Column(name = "Role")
    private EmployeeRole role;


    @Column(name = "Position")
    private Position position;




    public User()
    {

    }

    public User(String userID, String userName, String userSurname, String userEmail, String userPassword, EmployeeRole role, Position position)
    {
        this.userID = userID;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.role = role;
        this.position = position;
    }

    public String getUserID()
    {
        return userID;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

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

    public EmployeeRole getRole()
    {
        return role;
    }

    public void setRole(EmployeeRole role)
    {
        this.role = role;
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
