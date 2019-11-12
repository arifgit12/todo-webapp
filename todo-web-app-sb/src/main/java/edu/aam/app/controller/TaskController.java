package edu.aam.app.controller;

import edu.aam.app.model.Todo;
import edu.aam.app.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TaskController {

    @Autowired
    private TaskService taskService;

    @RequestMapping(value = "/add-task", method = RequestMethod.GET)
    public String showAddTaskPage(ModelMap model) {
        model.addAttribute("task", null);
        return "tasks/task";
    }

    @RequestMapping(value = "/list-tasks", method = RequestMethod.GET)
    public String showTasks(ModelMap model) {
        model.put("tasks", taskService.getAllTasks());
        return "tasks/list-tasks";
    }
}
