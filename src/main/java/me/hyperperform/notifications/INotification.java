package me.hyperperform.notifications;

/**
 * Interface to make the notifications module fully pluggable and easy to use
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/10/12
 */

public interface INotification
{
    /**
     * Sends an email to the recipient
     *
     * @param to - the recipient
     * @param subject - the message subject
     * @param body - the actual content of the message
     * @return true - if successful false otherwise
     */
    public boolean sendMail(String to, String subject, String body);
}
