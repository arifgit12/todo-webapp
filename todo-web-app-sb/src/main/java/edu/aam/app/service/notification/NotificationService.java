package edu.aam.app.service.notification;

import edu.aam.app.model.Notification;
import edu.aam.app.model.User;
import edu.aam.app.repository.NotificationRepository;
import edu.aam.app.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NotificationService implements INotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    IUserService userService;

    @Override
    public Notification save(User user,String message,String link) {
        Notification notification = new Notification();
        notification.setLink(link);
        notification.setLocalDateTime(new Date());
        notification.setMessage(message);
        notification.setUser(user);
        return notificationRepository.save(notification);
    }

    @Override
    public int countUnseenNotifications(String username) {
        return notificationRepository.countAllByUserAndSeenIsFalse(userService.findUserByUserName(username));
    }

    @Override
    public List<Notification> getUnseenNotifications(String username) {
        return notificationRepository.findByUserAndSeenIsFalseOrderByLocalDateTimeDesc(userService.findUserByUserName(username));
    }

    @Override
    public void makeAllNotificationsSeenByUser(String username) {
        List<Notification> notifications = notificationRepository.findByUser(userService.findUserByUserName(username));
        for (Notification notification:notifications){
            notification.setSeen(true);
            notificationRepository.save(notification);
        }
    }
}
