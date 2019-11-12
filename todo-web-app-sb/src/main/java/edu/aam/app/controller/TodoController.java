package edu.aam.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.Valid;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;
import edu.aam.app.service.ITodoService;
import edu.aam.app.service.task.ITaskService;
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

	@RequestMapping(value = "/task-add-todo", method = RequestMethod.GET)
	public String showAddTodoPageByTask(@RequestParam Long taskId,  ModelMap model) {
		TodoViewModel todoViewModel = new TodoViewModel();
		todoViewModel.setTaskId(taskId);
		model.addAttribute("todo", todoViewModel);
		return "todos/todo";
	}

	@RequestMapping(value = "/task-add-todo", method = RequestMethod.POST)
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

	@RequestMapping(value = "/add-todo", method = RequestMethod.GET)
	public String showAddTodoPage(ModelMap model) {
		model.addAttribute("todo", new Todo());
		return "todo";
	}

	@RequestMapping(value = "/delete-todo", method = RequestMethod.GET)
	public String deleteTodo(@RequestParam long id) {
		todoService.deleteTodo(id);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam long id, ModelMap model) {
		Todo todo = todoService.getTodoById(id).get();
		model.put("todo", todo);
		return "todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		todo.setStatus(false);
		todo.setUserName(getLoggedInUserName(model));
		todo.setUpdatedDate(new Date());
		todoService.updateTodo(todo);
		return "redirect:/list-todos";
	}

	@RequestMapping(value = "/add-todo", method = RequestMethod.POST)
	public String addTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

		if (result.hasErrors()) {
			return "todo";
		}

		todo.setUserName(getLoggedInUserName(model));
		todo.setStatus(false);
		todo.setCreatedDate(new Date());
		todoService.saveTodo(todo);
		model.clear();
		return "redirect:/list-todos";
	}

//	@RequestMapping(value = "/task-add-todo", method = RequestMethod.POST)
//	public String taskAddTodo(ModelMap model, @Valid TodoViewModel todoViewModel, BindingResult result) {
//
//		if (result.hasErrors()) {
//			return "todos/todo";
//		}
//
//		Todo todo = new Todo();
//		todo.setTaskList(todoService.getTaskById(todoViewModel.getTaskId()));
//		todo.setDescription(todoViewModel.getDescription());
//		todo.setTargetDate(todoViewModel.getTargetDate());
//		todo.setUserName(getLoggedInUserName(model));
//		todo.setStatus(false);
//		todo.setCreatedDate(new Date());
//
//		model.clear();
//		return "redirect:/tasks/list-tasks";
//	}
}
