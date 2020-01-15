package edu.aam.app.service.todo;

import java.util.List;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;

public interface ITodoService {

	Todo getTodo(Long id);
	void deleteTodo(long id);
	void saveTodo(Todo todo);
	Task getTaskById(Long id);
	Todo putStatusTodo(Long id, Boolean status);
	List<Todo> getTodosByUser(String user);
	List<Todo> getTodoListByUserName(String username);
}