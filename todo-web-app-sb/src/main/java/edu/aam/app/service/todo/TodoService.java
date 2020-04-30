package edu.aam.app.service.todo;

import java.util.*;
import java.util.stream.Collectors;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;
import edu.aam.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.aam.app.repository.TodoRepository;

import javax.transaction.Transactional;

@Service
@Transactional
public class TodoService implements ITodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<Todo> getTodosByUser(String username) {
		return todoRepository.findOrderedTodo(username);
	}

	@Override
	public Todo getTodo(Long id) {
		return todoRepository.getOne(id);
	}

	@Override
	public Todo getTodo(Long id, String username) {
		return todoRepository.findUserTodoById(id, username);
	}

	@Override
	public void deleteTodo(long id) {
		Optional<Todo> todo = todoRepository.findById(id);
		if (todo.isPresent()) {
			todoRepository.delete(todo.get());
		}
	}

	@Override
	public Todo saveTodo(Todo newTodo) {
		Todo todo = todoRepository.save(newTodo);
		return todo;
	}

	@Override
	public Task getTaskById(Long id) {
		return taskRepository.getOne(id);
	}

	@Override
	public Todo putStatusTodo(Long id, Boolean status) {
		Todo todo = todoRepository.getOne(id);
		todo.setStatus(status);
		todo.setUpdatedDate(new Date());
		todoRepository.save(todo);
		return todo;
	}

	@Override
	public List<Todo> getTodoListByUserName(String username) {

		List<Todo> todoList = todoRepository.findAll().stream()
				.filter( td -> td.getTaskList().getUser().getEmail().contentEquals(username))
				.sorted(Comparator.comparing(Todo::getTargetDate).reversed())
				.sorted(Comparator.comparing(Todo::getStatus))
				.collect(Collectors.toList());

		return todoList;
	}
}