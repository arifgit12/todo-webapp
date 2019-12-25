package edu.aam.app.controller;

import edu.aam.app.model.Task;
import edu.aam.app.service.task.TaskService;
import edu.aam.app.service.task.TaskViewModel;
import edu.aam.app.service.user.UserService;
import edu.aam.app.util.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add-task", method = RequestMethod.GET)
    public String showAddTaskPage(ModelMap model) {
        model.addAttribute("task", new Task());
        return "tasks/task";
    }

    @RequestMapping(value = "/add-task", method = RequestMethod.POST)
    public String addTask(ModelMap model, @Valid Task task, BindingResult result) {

        if (result.hasErrors()) {
            return "tasks/task";
        }

        task.setDescription(task.getDescription());
        task.setTaskName(task.getTaskName());
        taskService.save(task);
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
    public String updateTask(@RequestParam long taskId, ModelMap model, @Valid TaskViewModel taskViewModel, BindingResult result) {

        if (result.hasErrors()) {
            model.addAttribute("task", taskViewModel);
            return "tasks/update-task";
        }

        taskService.putTask(taskViewModel.getId(), taskViewModel);

        return "redirect:/list-tasks";
    }
}
