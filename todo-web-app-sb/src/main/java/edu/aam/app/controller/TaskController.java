package edu.aam.app.controller;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;
import edu.aam.app.service.task.TaskService;
import edu.aam.app.service.task.TaskViewModel;
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
import java.util.Date;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/add-task", method = RequestMethod.GET)
    public String showAddTaskPage(ModelMap model) {
        model.addAttribute("task", new Task());
        return "tasks/task";
    }

    @RequestMapping(value = "/add-task", method = RequestMethod.POST)
    public String addTodo(ModelMap model, @Valid Task task, BindingResult result) {

        if (result.hasErrors()) {
            return "tasks/task";
        }

        task.setUserName(getLoggedInUserName(model));
        task.setDescription(task.getDescription());
        task.setTaskName(task.getTaskName());
        taskService.save(task);
        model.clear();
        return "redirect:/list-tasks";
    }

    @RequestMapping(value = "/list-tasks", method = RequestMethod.GET)
    public String showTasks(ModelMap model) {
        model.put("tasks", taskService.getAllTasks());
        return "tasks/list-tasks";
    }

    @RequestMapping(value = "/update-task", method = RequestMethod.GET)
    public String showUpdateTaskPage(@RequestParam long taskId, ModelMap model) {
        TaskViewModel taskViewModel = taskService.mapTaskViewModel(taskId);
        taskViewModel.setId(taskId);
        model.addAttribute("task", taskViewModel);
        return "tasks/task";
    }

    @RequestMapping(value = "/update-task", method = RequestMethod.POST)
    public String updateTask(ModelMap model, @Valid TaskViewModel taskViewModel, BindingResult result) {

        if (result.hasErrors()) {
            return "tasks/task";
        }

        taskService.putTask(taskViewModel.getId(), taskViewModel);

        return "redirect:/list-tasks";
    }

    private String getLoggedInUserName(ModelMap model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }
}
