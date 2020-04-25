package edu.aam.app.controller;

import edu.aam.app.model.User;
import edu.aam.app.service.notification.INotificationService;
import edu.aam.app.service.user.IUserService;
import edu.aam.app.service.user.PasswordDTO;
import edu.aam.app.service.user.UserDTO;
import edu.aam.app.service.user.UserService;
import edu.aam.app.util.AuthenticatedUser;
import edu.aam.app.validator.PasswordValidator;
import edu.aam.app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    INotificationService notificationService;

    @GetMapping("/delete/{email}")
    public String delete(@PathVariable String email) {
        return "";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getProfile() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/profile/user-details");
        User user = userService.findUserByEmail(AuthenticatedUser.findLoggedInUsername());
        view.addObject("profile", userService.convertUserDto(user));
        view.addObject("notification_number_todo", notificationService.countUnseenNotifications(AuthenticatedUser.findLoggedInUsername()));
        return view;
    }

    @RequestMapping(value = "/update-password", method = RequestMethod.GET)
    public String changePassword(Model model) {
        model.addAttribute("pwd", new PasswordDTO("", "", ""));
        model.addAttribute("notification_number_todo", notificationService.countUnseenNotifications(AuthenticatedUser.findLoggedInUsername()));
        return "/profile/update-password";
    }

    @RequestMapping(value = "/update-password", method = RequestMethod.POST)
    public String changePassword(@ModelAttribute("pwd") PasswordDTO pwdForm,
                                 BindingResult bindingResult, Model model) {

        passwordValidator.validate(pwdForm, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("pwd", pwdForm);
            return "/profile/update-password";
        }

        userService.updatePassword(AuthenticatedUser.findLoggedInUsername(), pwdForm.getNewPassword());

        return "redirect:/profile";
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("notification_number_todo", notificationService.countUnseenNotifications(AuthenticatedUser.findLoggedInUsername()));
        return "/users/list-users";
    }
}
