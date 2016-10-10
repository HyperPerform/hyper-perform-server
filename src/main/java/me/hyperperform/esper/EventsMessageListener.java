package me.hyperperform.esper;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

/**
 * Esper CEP event listener that finds patterns in performance based on a given criteria at real-time
 * This feature is still under development and is a future enhancement.
 *
 * @author  CodusMaximus
 * @version 1.0
 * @since   2016/07/06
 */

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "hyperperform")
})
public class EventsMessageListener  implements MessageListener
{
    public void onMessage(Message message)
    {
        try
        {
            if (message instanceof ObjectMessage)
                System.out.println(((ObjectMessage) message).getObject());
        }

        catch (JMSException j)
        {
            System.out.println("Failed to retrieve event from queue");
        }
    }
}
