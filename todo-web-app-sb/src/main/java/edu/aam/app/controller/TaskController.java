package edu.aam.app.controller;

import edu.aam.app.service.task.ITaskService;
import edu.aam.app.service.task.TaskViewModel;
import edu.aam.app.service.user.UserService;
import edu.aam.app.util.AuthenticatedUser;
import edu.aam.app.validator.TaskValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class TaskController {

    @Autowired
    private ITaskService taskService;

    @Autowired
    private UserService userService;

    @Autowired
    private TaskValidator taskValidator;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(taskValidator);
    }

    @RequestMapping(value = "/add-task", method = RequestMethod.GET)
    public String showAddTaskPage(ModelMap model) {
        model.addAttribute("task", new TaskViewModel());
        return "tasks/task";
    }

    @RequestMapping(value = "/add-task", method = RequestMethod.POST)
    public String addTask(ModelMap model, @ModelAttribute("task") TaskViewModel taskViewModel, BindingResult bindingResult) {

        taskValidator.validate(taskViewModel, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("task", taskViewModel);
            return "tasks/task";
        }

        taskService.save(taskViewModel, AuthenticatedUser.findLoggedInUsername());
        model.clear();
        return "redirect:/list-tasks";
    }

    @RequestMapping(value = "/list-tasks", method = RequestMethod.GET)
    public String showTasks(ModelMap model) {
        model.put("tasks", userService.findTaskByUserEmail(AuthenticatedUser.findLoggedInUsername()));
        return "tasks/list-tasks";
    }

    @RequestMapping(value = "/update-task", method = RequestMethod.GET)
    public String showUpdateTaskPage(@RequestParam long taskId, ModelMap model) {
        TaskViewModel taskViewModel = taskService.mapTaskViewModel(taskId, AuthenticatedUser.findLoggedInUsername());

        if (taskViewModel != null) {
            model.addAttribute("task", taskViewModel);
        }

        return "tasks/update-task";
    }

    @RequestMapping(value = "/update-task", method = RequestMethod.POST)
    public String updateTask(@RequestParam long taskId, ModelMap model, @ModelAttribute("task") TaskViewModel taskViewModel, BindingResult bindingResult) {

        taskValidator.validate(taskViewModel, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("task", taskViewModel);
            return "tasks/update-task";
        }

        taskService.putTask(taskViewModel.getId(), taskViewModel);

        return "redirect:/list-tasks";
    }
}
