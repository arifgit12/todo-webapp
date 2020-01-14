package edu.aam.app.validator;

import edu.aam.app.model.User;
import edu.aam.app.service.user.PasswordDTO;
import edu.aam.app.service.user.UserService;
import edu.aam.app.util.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class PasswordValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return PasswordDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        PasswordDTO userPassword = (PasswordDTO) o;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        User user = userService.findUserByEmail(AuthenticatedUser.findLoggedInUsername());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "currentPassword", "NotEmpty");
        if (!passwordEncoder.matches(userPassword.getCurrentPassword(), user.getPassword())) {
            errors.rejectValue("currentPassword", "passwordNotMatched");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "newPassword", "NotEmpty");
        if (userPassword.getNewPassword().length() < 5 || userPassword.getNewPassword().length() > 22) {
            errors.rejectValue("newPassword", "Size.userForm.password");
        }

        if (!userPassword.getConfirmPassword().equals(userPassword.getNewPassword())) {
            errors.rejectValue("confirmPassword", "Diff.userForm.passwordConfirm");
        }
    }
}
