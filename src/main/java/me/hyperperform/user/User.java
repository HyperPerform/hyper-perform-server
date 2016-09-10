package me.hyperperform.user;

import javax.persistence.*;
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
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "id")
//    private String id = null;
    @Id
    @Column(name = "Email")
    private String userEmail = null;

    @Column(name = "Username")
    private String userName = null;

    @Column(name = "Name")
    private String name = null;

    @Column(name = "Surname")
    private String surname = null;

    @Column(name = "Password")
    private String userPassword = null;

    @Column(name = "ProfilePicture")
    private Byte[] profilePicture = null;

    @Column(name = "Role")
    private EmployeeRole role;

    @Column(name = "Position")
    private Position position;




    public User()
    {

    }

    public User(String userEmail, String userName, String name, String surname, String userPassword, Byte[] profilePicture, EmployeeRole role, Position position)
    {
        this.userEmail = userEmail;
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.userPassword = userPassword;
        this.profilePicture = profilePicture;
        this.role = role;
        this.position = position;
    }

    public String getUserEmail()
    {
        return userEmail;
    }

    public void setUserEmail(String userEmail)
    {
        this.userEmail = userEmail;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getSurname()
    {
        return surname;
    }

    public void setSurname(String surname)
    {
        this.surname = surname;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public Byte[] getProfilePicture()
    {
        return profilePicture;
    }

    public void setProfilePicture(Byte[] profilePicture)
    {
        this.profilePicture = profilePicture;
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
