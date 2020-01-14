package edu.aam.app.controller;

import edu.aam.app.model.User;
import edu.aam.app.service.user.PasswordDTO;
import edu.aam.app.service.user.UserDTO;
import edu.aam.app.service.user.UserService;
import edu.aam.app.util.AuthenticatedUser;
import edu.aam.app.validator.PasswordValidator;
import edu.aam.app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
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
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @InitBinder("userForm")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(userValidator);
    }

    @InitBinder("pwd")
    protected void pwdInitBinder(WebDataBinder binder) {
        binder.addValidators(passwordValidator);
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.DTOsave(userForm);

        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @GetMapping("/delete/{email}")
    public String delete(@PathVariable String email) {
        return "";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public ModelAndView getProfile() {
        ModelAndView view = new ModelAndView();
        view.setViewName("/profile/user-details");
        User user = userService.findUserByEmail(AuthenticatedUser.findLoggedInUsername());
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        view.addObject("profile", userDTO);
        return view;
    }

    @RequestMapping(value = "/update-password", method = RequestMethod.GET)
    public String changePassword(Model model) {
        model.addAttribute("pwd", new PasswordDTO("", "", ""));
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
}
