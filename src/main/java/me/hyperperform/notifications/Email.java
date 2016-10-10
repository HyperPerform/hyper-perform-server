package me.hyperperform.notifications;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


 /**
  * Email is a class that gets configuration for the application server wildfly this requires
  * the server to be setup correctly as thw user manual states
  *
  * @author  CodusMaximus
  * @version 1.0
  * @since   2016/10/06
  */


@Stateless
public class Email
{
    /**
     * This uses JavaEE to pull the configuration from the wildfly application server
     */
    @Resource(name = "java:jboss/mail/gmail")
    private Session session;

    public Email()
    {
    }

    /**
     * This function is a simple setup to send html emails
     *
     * @param to - the recipient to who the email must be sent to
     * @param subject - the subject of the email
     * @param body - the actual html contents to be sent to the recipient
     * @return true or false - based on if it sent successfully or not
     */
    public boolean sendMail(String to, String subject, String body)
    {
        try
        {
            Message m = new MimeMessage(session);
            m.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            m.setSubject(subject);
            m.setContent(body, "text/html");


            Transport.send(m);
        }
        catch (Exception e)
        {
//            e.printStackTrace();
            System.out.println("Failed to send email to: " + to);
            return false;
        }
        return true;
    }


}
