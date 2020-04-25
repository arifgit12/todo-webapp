package edu.aam.app.controller;

import edu.aam.app.service.notification.INotificationService;
import edu.aam.app.util.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NotificationController {

    @Autowired
    INotificationService notificationService;

    @ModelAttribute("notification_number_todo")
    private int getNotificationNumber(){
        return notificationService.countUnseenNotifications(AuthenticatedUser.findLoggedInUsername());
    }

    @RequestMapping(value = "/nf-list", method = RequestMethod.GET)
    public String showNotifications(ModelMap model) {
        model.put("notifications", notificationService.getUnseenNotifications(AuthenticatedUser.findLoggedInUsername()));
        return "list-notifications";
    }

    @RequestMapping(value = "/remove-list", method = RequestMethod.GET)
    public String removeNotifications(ModelMap model) {
        notificationService.makeAllNotificationsSeenByUser(AuthenticatedUser.findLoggedInUsername());
        return "redirect:/";
    }
}
