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
    @Column(name = "Position")
    private String position = null;

    @Column(name = "isAdmin")
    private boolean isAdmin = false;



    public User()
    {

    }

    public User(String userID, String userName, String userSurname, String userEmail, String userPassword, String position, Boolean isAdmin)
    {
        this.userID = userID;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
//        this.profilePicture = profilePicture;
        this.position = position;
        this.isAdmin = isAdmin;
    }

    public String getPosition()
    {
        return position;
    }

    public void setPosition(String position)
    {
        this.position = position;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public void setUserSurname(String userSurname)
    {
        this.userSurname = userSurname;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public void setUserID(String userID)
    {
        this.userID = userID;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public void setIsAdmin(boolean isAdmin)
    {
        this.isAdmin = isAdmin;
    }

//    public Image getProfilePicture()
//    {
//        return profilePicture;
//    }

    public boolean getIsAdmin()
    {
        return isAdmin;
    }

    public String getUserName()
    {
        return userName;
    }

    public String getUserSurname()
    {
        return userSurname;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public String getUserID()
    {
        return userID;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

//    public void setProfilePicture(Image profilePicture)
//    {
//        this.profilePicture = profilePicture;
//    }



}
