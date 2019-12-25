package edu.aam.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;
import edu.aam.app.service.comment.CommentViewModel;
import edu.aam.app.service.task.ITaskService;
import edu.aam.app.service.todo.ITodoService;
import edu.aam.app.service.todo.TodoViewModel;
import edu.aam.app.service.user.UserService;
import edu.aam.app.util.AuthenticatedUser;
import edu.aam.app.validator.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
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

	@Autowired
	private ITaskService taskService;

	@Autowired
	private UserService userService;

	@Autowired
	private TodoValidator todoValidator;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(todoValidator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public String showTodo(@RequestParam Long id, ModelMap model) {
		String name = getLoggedInUserName(model);
		Todo todo = userService.getTodoByUserName(name, id);
		if (todo != null) {
			model.put("taskname", todo.getTaskList().getTaskName());
			model.put("todo", todo);
			model.put("commentDTO", new CommentViewModel());
		}
		return "todos/todo-details";
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		String name = getLoggedInUserName(model);
		model.put("todos", todoService.getTodoListByUserName(name));
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
		Task task = taskService.getTaskByUserName(taskId, AuthenticatedUser.findLoggedInUsername());
		if(task != null) {
			model.put("taskId", task.getId());
			model.put("taskname", task.getTaskName());
			model.put("todos", task.getTodoList());
		}

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
	public String addTodoByTask(ModelMap model, @ModelAttribute("todo") TodoViewModel todoViewModel, BindingResult bindingResult) {

		Task task = todoService.getTaskById(todoViewModel.getTaskId());

		todoValidator.validate(todoViewModel, bindingResult);
		if (bindingResult.hasErrors()) {
			model.put("taskname", task.getTaskName());
			model.addAttribute("todo", todoViewModel);
			return "todos/todo";
		}

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
		model.put("taskname", todo.getTaskList().getTaskName());
		return "todos/todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodoByTask(ModelMap model, @ModelAttribute("todo") TodoViewModel todoViewModel, BindingResult bindingResult) {

		Todo todo = todoService.getTodo(todoViewModel.getTodoId());

		todoValidator.validate(todoViewModel, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("todo", todoViewModel);
			model.put("taskname", todo.getTaskList().getTaskName());
			return "todos/todo";
		}

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

	@RequestMapping(value = "/checked/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Todo> checked(@PathVariable("id") Long id, @RequestParam boolean complete) {
		todoService.putStatusTodo(id, complete);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName(); //get logged in username
		List<Todo> todos = todoService.getTodosByUser(username);
		return todos;
	}
}
