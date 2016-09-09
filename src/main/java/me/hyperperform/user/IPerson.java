package me.hyperperform.user;



/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/05
 * Feature:
 */
public interface    IPerson
{
    String getUserName();

    String getUserSurname();

    String getUserEmail();

    String getUserPassword();

    String getUserID();



    void setUserName(String userName);

    void setUserSurname(String userSurname);

    void setUserEmail(String userEmail);

    void setUserID(String userID);

    void setUserPassword(String userPassword);
}
