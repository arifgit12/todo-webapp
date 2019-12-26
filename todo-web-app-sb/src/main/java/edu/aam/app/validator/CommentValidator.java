package edu.aam.app.validator;

import edu.aam.app.service.comment.CommentViewModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CommentValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CommentViewModel.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

    }
}
