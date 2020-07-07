package edu.aam.app.controller;

import edu.aam.app.model.ConfirmationToken;
import edu.aam.app.model.User;
import edu.aam.app.service.account.IAccountService;
import edu.aam.app.service.email.IEmailService;
import edu.aam.app.service.user.IUserService;
import edu.aam.app.service.user.UserDTO;
import edu.aam.app.util.ServerUtils;
import edu.aam.app.validator.PasswordValidator;
import edu.aam.app.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class AuthController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    @Qualifier("sendGridEmailService")
    private IEmailService emailService;

    @Autowired
    private IAccountService accountService;

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

    @RequestMapping(value = "/register",  method= RequestMethod.POST)
    public String register(HttpServletRequest request,
                           @ModelAttribute("userForm") UserDTO userForm,
                           BindingResult bindingResult, Model model) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "register";
        }

        User user = userService.createUser(userForm);

        if (user == null) {
            model.addAttribute("error", "Unable to Register User");
            return "register";
        }

        emailService.sendRegisterEmail(user, ServerUtils.getServerUrl(request));

        model.addAttribute("email", user.getEmail());
        return "successfulRegisteration";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request,
                         HttpServletResponse response) {

        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response,
                    authentication);
        }

        return "redirect:/";
    }

    @RequestMapping(value = "/forgetpassword", method = RequestMethod.GET)
    public String forgetPassword(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "forgetPassword";
    }

    @RequestMapping(value = "/forgetpassword", method = RequestMethod.POST)
    public String forgetPassword(HttpServletRequest request, @ModelAttribute("userForm") UserDTO userForm, BindingResult bindingResult) {

        String appUrl = ServerUtils.getServerUrl(request) +"/login";
        emailService.sendForgetPasswordEmail(userForm.getEmail(), appUrl);
        return "redirect:/login";
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken) {

        ConfirmationToken token = accountService.getConfirmationToken(confirmationToken);

        if(token != null) {
            if(token.getUser().isEnabled()) {
                modelAndView.addObject("verifiedMessage","Your email has been already verified!");
            } else {
                userService.updateToken(token.getUser().getEmail(), true);
                modelAndView.addObject("verifiedMessage","Congratulations! Your account has been activated and email is verified!");
            }
            modelAndView.setViewName("accountVerified");
        } else {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("registerError");
        }

        return modelAndView;
    }
}
