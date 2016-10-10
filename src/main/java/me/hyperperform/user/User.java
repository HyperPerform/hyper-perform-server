package me.hyperperform.user;

import me.hyperperform.Hash;

import javax.persistence.*;
import java.awt.*;

/**
 * User POJO that stores all the users information that uses the hyperperform system
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/05
 */

@Entity
@Table(name = "\"User\"")
public class User implements IPerson
{
    /**
     * Primary key because a single user can only have one email address
     */
    @Id
    @Column(name = "Email")
    private String userEmail = null;

    @Column(name = "GitUsername")
    private String gitUserName = null;

    @Column(name = "Username")
    private String userName = null;

    @Column(name = "Name")
    private String name = null;

    @Column(name = "Surname")
    private String surname = null;

    @Column(name = "Password")
    private String userPassword = null;

    /**
     *  Attribute that stores the users profile at a byte level
     */
    @Column(name = "ProfilePicture")
    private Byte[] profilePicture = null;

    /**
     * Enum that defines roles for the system
     * {@see me.hyperperform.user.EmployeeRole}
     */
    @Column(name = "Role")
    private EmployeeRole role;

    /**
     * Enum that defines positions of the user\
     * {@see me.hyperperform.user.Position}
     */
    @Column(name = "Position")
    private Position position;




    public User()
    {

    }

    /**
     * Constructor initializing the user pojo
     * @param userEmail - email of the new user
     * @param gitUserName - the users GitHub username
     * @param userName - an optional username for the business
     * @param name - name of the new user
     * @param surname - surname of the new user
     * @param userPassword - password of the user that is hashed {@see me.hyperperform.user.Hash}
     * @param profilePicture - sets the profile picture of the user at a byte level
     * @param role - the role given so permissions can be checked accordingly
     * @param position - the position of the user {@see me.hyperperform.user.Position}
     */
    public User(String userEmail, String gitUserName, String userName, String name, String surname, String userPassword, Byte[] profilePicture, EmployeeRole role, Position position)
    {
        this.userEmail = userEmail;
        this.gitUserName = gitUserName;
        this.userName = userName;
        this.name = name;
        this.surname = surname;
        this.userPassword = Hash.gethash(userPassword);
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

    public String getGitUserName()
    {
        return gitUserName;
    }

    public void setGitUserName(String gitUserName)
    {
        this.gitUserName = gitUserName;
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
        this.userPassword = Hash.gethash(userPassword);
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
