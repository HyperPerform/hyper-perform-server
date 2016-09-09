package me.hyperperform.notification;

import me.hyperperform.notification.request.ChangePasswordNotificationRequest;
import me.hyperperform.notification.request.NewUserNotificationRequest;
import me.hyperperform.notification.response.ChangePasswordNotificationResponse;
import me.hyperperform.notification.response.NewUserNotificationResponse;

import javax.mail.*;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/09
 * Feature:
 */
public class Notifications implements INotification
{
    public NewUserNotificationResponse newUserNotification(NewUserNotificationRequest newUserNotificationRequest)
    {
        return null;
    }

    public ChangePasswordNotificationResponse changePasswordNotification(ChangePasswordNotificationRequest changePasswordNotificationRequest)
    {
        return null;
    }
}
