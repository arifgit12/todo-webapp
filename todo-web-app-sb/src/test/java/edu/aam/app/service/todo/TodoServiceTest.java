package edu.aam.app.service.todo;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;
import edu.aam.app.model.User;
import edu.aam.app.repository.TaskRepository;
import edu.aam.app.repository.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    private ITodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private TaskRepository taskRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        todoService = new TodoService();
        Whitebox.setInternalState(todoService, "todoRepository", todoRepository);
        Whitebox.setInternalState(todoService, "taskRepository", taskRepository);
    }

    private Todo setTodo() {
        Todo todo = new Todo();
        todo.setTaskList(setTask());
        todo.setDescription("Todo t1");
        todo.setCreatedDate(new Date());
        return todo;
    }

    private Todo setTodo(String todoName) {
        Todo todo = new Todo();
        todo.setTaskList(setTask());
        todo.setDescription(todoName);
        todo.setCreatedDate(new Date());
        return todo;
    }

    private Task setTask() {
        Task task = new Task();
        task.setUser(setUser());
        task.setTaskName("task1");
        task.setDescription("Task Description NO1");
        return task;
    }

    private User setUser() {
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("testmail@gmail.com");
        return user;
    }

    @Test
    public void getTodoTest1() {
        Todo result = todoService.getTodo(0L);
        assertNull(result);
    }

    @Test
    public void getTodoTest2() {
        Todo todo = setTodo();
        when(todoRepository.getOne(1L)).thenReturn(todo);
        Todo result = todoService.getTodo(1L);
        assertNotNull(result);
    }

    @Test
    public void getTodosByUserTest() {
        Todo todoExampe1 = setTodo("Todo Example 1");
        Todo todoExample2 = setTodo("Todo Example 2");
        List<Todo> dummyList = Arrays.asList(todoExampe1, todoExample2);
        when(todoRepository.findOrderedTodo(anyString())).thenReturn(dummyList);
        List<Todo> results = todoRepository.findOrderedTodo(setUser().getEmail());
        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    public void findUserTodoByIdTest() {
        Todo todo = setTodo();
        when(todoRepository.findUserTodoById(anyLong(), anyString())).thenReturn(todo);
        Todo result = todoService.getTodo(1L, "test@mail.com");
        assertNotNull(result);
    }
}
