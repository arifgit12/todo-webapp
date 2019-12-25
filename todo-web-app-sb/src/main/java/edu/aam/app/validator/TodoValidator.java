package edu.aam.app.validator;

import edu.aam.app.service.todo.TodoViewModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TodoValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return TodoViewModel.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TodoViewModel tvm = (TodoViewModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");

        if (tvm.getDescription().length() > 0 && tvm.getDescription().length() < 10) {
            errors.rejectValue("description", "Size.todo.description");
        }
    }
}
