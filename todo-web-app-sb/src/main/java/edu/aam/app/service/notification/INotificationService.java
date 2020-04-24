package edu.aam.app.service.notification;

import edu.aam.app.model.Notification;
import edu.aam.app.model.User;

import java.util.List;

public interface INotificationService {
    Notification save(User user, String message, String link);
    int countUnseenNotifications(String username);
    List<Notification> getUnseenNotifications(String username);
    void makeAllNotificationsSeenByUser(String username);
}
