package edu.aam.app.repository;

import edu.aam.app.model.Notification;
import edu.aam.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    int countAllByUserAndSeenIsFalse(User user);
    List<Notification> findByUserOrderByLocalDateTimeDesc(User user);
    List<Notification> findByUserAndSeenIsFalseOrderByLocalDateTimeDesc(User user);
    List<Notification> findByUser(User user);
}
