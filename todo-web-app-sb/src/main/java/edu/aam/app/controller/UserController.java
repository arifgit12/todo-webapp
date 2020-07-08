package edu.aam.app.controller;

import edu.aam.app.model.Log;
import edu.aam.app.model.User;
import edu.aam.app.model.enums.LogType;
import edu.aam.app.service.log.LogService;
import edu.aam.app.service.notification.INotificationService;
import edu.aam.app.service.user.IUserService;
import edu.aam.app.service.user.PasswordDTO;
import edu.aam.app.model.vm.UserDTO;
import edu.aam.app.util.AuthenticatedUser;
import edu.aam.app.util.FilenameUtils;
import edu.aam.app.util.ServletUtils;
import edu.aam.app.validator.PasswordValidator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class UserController {

    @Value("${image.path}")
    private String UPLOADED_FOLDER;

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private LogService logService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/delete/{email}")
    public String delete(@PathVariable String email) {
        return "";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getProfile() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/profile/user-details");
        UserDTO userDTO = modelMapper.map(userService.findUserByEmail(AuthenticatedUser.findLoggedInUsername()), UserDTO.class);
        view.addObject("profile", userDTO);
        view.addObject("notification_number_todo", notificationService.countUnseenNotifications(AuthenticatedUser.findLoggedInUsername()));
        return view;
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.GET)
    public String editProfile(ModelMap model) {

        UserDTO userForm = modelMapper.map(userService.findUserByEmail(AuthenticatedUser.findLoggedInUsername()), UserDTO.class);
        model.addAttribute("profile", userForm);
        model.addAttribute("notification_number_todo", notificationService.countUnseenNotifications(AuthenticatedUser.findLoggedInUsername()));

        return "profile/edit";
    }

    @RequestMapping(value = "/profile/edit", method = RequestMethod.POST)
    public String editProfile(@ModelAttribute("profile") UserDTO userForm,
        @RequestParam("imageFile") MultipartFile file,
        ModelMap model,
        BindingResult bindingResult) {

        User user = userService.findUserByEmail(AuthenticatedUser.findLoggedInUsername());

        model.addAttribute("profile", userForm);
        model.addAttribute("notification_number_todo", notificationService.countUnseenNotifications(AuthenticatedUser.findLoggedInUsername()));

        if (file.isEmpty() && (user.getAvatar() == null || user.getAvatar().length() < 1)) {
            model.addAttribute("message", "Please select a file to upload");
            return "profile/edit";
        }

        if (bindingResult.hasErrors()) {
            return "profile/edit";
        }

        user.setFirstName(userForm.getFirstName());
        user.setLastName(userForm.getLastName());
        user.setDescription(userForm.getDescription());

        try {
            if (file.getOriginalFilename() != null && file.getOriginalFilename().length() > 1) {
                byte[] bytes = file.getBytes();
                String imageName = FilenameUtils.getRandomName() +
                        "." + FilenameUtils.getExtension(file.getOriginalFilename());
                Path path = Paths.get(UPLOADED_FOLDER + imageName);
                Files.write(path, bytes);
                user.setAvatar(imageName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        userService.updateUser(user);

        Log log = new Log();
        log.setLogKey(user.getEmail());
        log.setType(LogType.PROFILE_UPDATED);
        log.setContent("Profile Updated");
        log.setIpAddress(ServletUtils.getRequestIp());
        logService.createLog(log);

        return "redirect:/profile";
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