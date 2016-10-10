package me.hyperperform.user;



/**
 * Defines a contract in which an organisation can easily switch a User POJO with a way in which they want to
 *
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/09/05
 */

public interface IPerson
{
    /**
     *
     * @return the username from the realised class
     */
    String getUserName();

    /**
     *
     * @return the hashed user password from the realised class
     */
    String getUserPassword();


    /**
     *
     * @return the email of the user
     */
    String getUserEmail();


    /**
     *
     * @param userName - sets the username for the realised class
     */
    void setUserName(String userName);


    /**
     *
     * @param userEmail - sets the user's email for the realised class
     */
    void setUserEmail(String userEmail);

    /**
     *
     * @param userPassword - sets the password in raw text the realised class will hash the password and persist it
     */
    void setUserPassword(String userPassword);
}
