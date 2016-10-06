package me.hyperperform.notifications;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/10/06
 * Feature:
 */

@Stateless
public class Email
{

    @Resource(name = "java:jboss/mail/gmail")
    private Session session;

    public Email()
    {
    }

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
