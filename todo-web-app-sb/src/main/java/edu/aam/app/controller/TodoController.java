package edu.aam.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.aam.app.model.Comment;
import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;
import edu.aam.app.service.comment.CommentViewModel;
import edu.aam.app.service.notification.INotificationService;
import edu.aam.app.service.task.ITaskService;
import edu.aam.app.service.todo.ITodoService;
import edu.aam.app.service.todo.TodoViewModel;
import edu.aam.app.util.AuthenticatedUser;
import edu.aam.app.validator.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TodoController {

	@Autowired
	private ITodoService todoService;

	@Autowired
	private ITaskService taskService;

	@Autowired
	private TodoValidator todoValidator;

	@Autowired
	INotificationService notificationService;

	@ModelAttribute("notification_number_todo")
	private int getNotificationNumber() {
		try {
			return notificationService.countUnseenNotifications(AuthenticatedUser.findLoggedInUsername());
		} catch (Exception ex) {
			//System.out.println(ex.getLocalizedMessage());
		}
		return 0;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(new TodoValidator());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}

	@RequestMapping(value = "/todo", method = RequestMethod.GET)
	public ModelAndView showTodo(@RequestParam Long id) {

		ModelAndView modelAndView = new ModelAndView("todos/todo-details");
		Todo todo = todoService.getTodo(id, AuthenticatedUser.findLoggedInUsername());
		List<CommentViewModel> cvmList = new ArrayList<>();
		if (todo != null) {
			modelAndView.addObject("taskname", todo.getTaskList().getTaskName());
			TodoViewModel todovm = new TodoViewModel();
			todovm.setTaskId(todo.getTaskList().getId());
			todovm.setTodoId(todo.getId());
			todovm.setDescription(todo.getDescription());
			todovm.setStatus(todo.getStatus());
			todovm.setTargetDate(todo.getTargetDate());
			modelAndView.addObject("todovm", todovm);
			for (Comment comment : todo.getComments()) {
				CommentViewModel cvm = new CommentViewModel();
				cvm.readCommentInfo(comment);
				cvmList.add(cvm);
			}
			modelAndView.addObject("cvmList", cvmList);
			modelAndView.addObject("commentDTO", new TodoViewModel());
		} else {
			modelAndView.addObject("nodata_found", "No Data Found");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/list-todos", method = RequestMethod.GET)
	public String showTodos(ModelMap model) {
		model.put("todos", todoService.getTodosByUser(AuthenticatedUser.findLoggedInUsername()));
		return "list-todos";
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
		return "todos/update-todo";
	}

	@RequestMapping(value = "/update-todo", method = RequestMethod.POST)
	public String updateTodoByTask(ModelMap model, @ModelAttribute("todo") TodoViewModel todoViewModel, BindingResult bindingResult) {

		Todo todo = todoService.getTodo(todoViewModel.getTodoId());

		todoValidator.validate(todoViewModel, bindingResult);

		if (bindingResult.hasErrors()) {
			model.addAttribute("todo", todoViewModel);
			model.put("taskname", todo.getTaskList().getTaskName());
			return "todos/update-todo";
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
		List<Todo> todos = todoService.getTodosByUser(AuthenticatedUser.findLoggedInUsername());
		return todos;
	}
}
