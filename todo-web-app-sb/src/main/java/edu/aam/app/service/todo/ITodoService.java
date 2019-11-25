package edu.aam.app.service.todo;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;

public interface ITodoService {

	List<Todo> getTodosByUser(String user);

	Optional<Todo> getTodoById(long id);

	Todo getTodo(Long id);

	void updateTodo(Todo todo);

	void addTodo(String name, String desc, Date targetDate, boolean isDone);

	void deleteTodo(long id);
	
	void saveTodo(Todo todo);

	Task getTaskById(Long id);

	Todo putStatusTodo(Long id, Boolean status);
}