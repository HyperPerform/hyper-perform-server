package me.hyperperform.notification;

import me.hyperperform.notification.request.ChangePasswordNotificationRequest;
import me.hyperperform.notification.request.NewUserNotificationRequest;
import me.hyperperform.notification.response.ChangePasswordNotificationResponse;
import me.hyperperform.notification.response.NewUserNotificationResponse;

/**
 * hyperperform-system
 * Group: CodusMaximus
 * Date: 2016/09/09
 * Feature:
 */
public interface INotification
{
    NewUserNotificationResponse newUserNotification(NewUserNotificationRequest newUserNotificationRequest);
    ChangePasswordNotificationResponse changePasswordNotification(ChangePasswordNotificationRequest changePasswordNotificationRequest);
}
