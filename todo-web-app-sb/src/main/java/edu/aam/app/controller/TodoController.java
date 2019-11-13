package edu.aam.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;
import edu.aam.app.service.todo.ITodoService;
import edu.aam.app.service.todo.TodoViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoController {

	@Autowired
	private ITodoService todoService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", todoService.getTodosByUser(name));
		return "list-todos";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	@RequestMapping(value = "/task-list-todos", method = RequestMethod.GET)
	public String showTodosByTask(@RequestParam Long taskId, ModelMap model) {
		Task task = todoService.getTaskById(taskId);
		model.put("taskId", task.getId());
		model.put("taskname", task.getTaskName());
		model.put("todos", task.getTodoList());
		return "todos/task-list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPageByTask(@RequestParam Long taskId,  ModelMap model) {
		Task task = todoService.getTaskById(taskId);
		TodoViewModel todoViewModel = new TodoViewModel();
		todoViewModel.setTaskId(task.getId());
		model.put("taskname", task.getTaskName());
		model.addAttribute("todo", todoViewModel);
		return "todos/todo";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodoByTask(ModelMap model, @Valid TodoViewModel todoViewModel, BindingResult result) {

		if (result.hasErrors()) {
			return "todos/todo";
		}

		Task task = todoService.getTaskById(todoViewModel.getTaskId());
		Todo todo = new Todo();
		todo.setDescription(todoViewModel.getDescription());
		todo.setTargetDate(todoViewModel.getTargetDate());
		todo.setUserName(getLoggedInUserName(model));
		todo.setTaskList(task);
		todo.setStatus(false);
		todo.setCreatedDate(new Date());
		todoService.saveTodo(todo);
		model.clear();
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPageByTask(@RequestParam long id, ModelMap model) {
		Todo todo = todoService.getTodo(id);
		TodoViewModel todoViewModel = new TodoViewModel();
		todoViewModel.setTodoId(todo.getId());
		todoViewModel.setTaskId(todo.getTaskList().getId());
		todoViewModel.setDescription(todo.getDescription());
		todoViewModel.setTargetDate(todo.getTargetDate());
		model.addAttribute("todo", todoViewModel);
		return "todos/todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodoByTask(ModelMap model, @Valid TodoViewModel todoViewModel, BindingResult result) {

		if (result.hasErrors()) {
			return "todos/todo";
		}
		Todo todo = todoService.getTodo(todoViewModel.getTodoId());
		todo.setDescription(todoViewModel.getDescription());
		todo.setTargetDate(todoViewModel.getTargetDate());
		todo.setStatus(false);
		todo.setUpdatedDate(new Date());
		todoService.saveTodo(todo);
		model.clear();
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam long id) {
		todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}
}
