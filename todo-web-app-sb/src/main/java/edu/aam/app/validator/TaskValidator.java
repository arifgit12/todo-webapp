package edu.aam.app.validator;

import edu.aam.app.service.task.TaskViewModel;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class TaskValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return TaskViewModel.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TaskViewModel taskViewModel = (TaskViewModel) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "taskName", "NotEmpty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty");

        if(taskViewModel.getTaskName().length() > 0 && taskViewModel.getTaskName().length() < 10 ) {
            errors.rejectValue("taskName", "Size.task.name");
        }

        if(taskViewModel.getDescription().length() > 0 && taskViewModel.getDescription().length() < 15 ) {
            errors.rejectValue("description", "Size.task.description");
        }
    }
}
