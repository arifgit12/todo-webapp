package edu.aam.app.controller;

import edu.aam.app.service.notification.INotificationService;
import edu.aam.app.util.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class WelcomeController {

	@Autowired
	INotificationService notificationService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model) {
		model.put("name", AuthenticatedUser.findLoggedInUsername());
		return "welcome";
	}

	@ModelAttribute("notification_number_todo")
	private int getNotificationNumber(){
		return notificationService.countUnseenNotifications(AuthenticatedUser.findLoggedInUsername());
	}
}
