package edu.aam.app.service.todo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;
import edu.aam.app.repository.TaskRepository;
import edu.aam.app.service.todo.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.aam.app.repository.TodoRepository;

@Service
public class TodoService implements ITodoService {

	@Autowired
	private TodoRepository todoRepository;

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public List<Todo> getTodosByUser(String user) {
		return todoRepository.findByUserName(user);
	}

	@Override
	public Optional<Todo> getTodoById(long id) {
		return todoRepository.findById(id);
	}

	@Override
	public Todo getTodo(Long id) {
		return todoRepository.getOne(id);
	}

	@Override
	public void updateTodo(Todo todo) {
		todoRepository.save(todo);
	}

	@Override
	public void addTodo(String name, String desc, Date targetDate, boolean isDone) {
		todoRepository.save(new Todo(name, desc, targetDate, isDone));
	}

	@Override
	public void deleteTodo(long id) {
		Optional<Todo> todo = todoRepository.findById(id);
		if (todo.isPresent()) {
			todoRepository.delete(todo.get());
		}
	}

	@Override
	public void saveTodo(Todo todo) {
		todoRepository.save(todo);
	}

	@Override
	public Task getTaskById(Long id) {
		return taskRepository.getOne(id);
	}

	@Override
	public void putStatusTodo(Long id) {
		Todo todo = todoRepository.getOne(id);
		todo.StatusUpdate(todo.getStatus());
		todoRepository.save(todo);
	}
}