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

    String getUserPassword();

    String getUserEmail();


    void setUserName(String userName);

    void setUserEmail(String userEmail);

    void setUserPassword(String userPassword);
}
