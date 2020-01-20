package edu.aam.app.service.todo;

import edu.aam.app.model.Task;
import edu.aam.app.model.Todo;
import edu.aam.app.model.User;
import edu.aam.app.repository.TodoRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import java.util.Date;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceTest {

    private ITodoService todoService;

    @Mock
    private TodoRepository todoRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        todoService = new TodoService();
        Whitebox.setInternalState(todoService, "todoRepository", todoRepository);
    }

    private Todo setTodo() {
        Todo todo = new Todo();
        todo.setTaskList(setTask());
        todo.setDescription("Todo t1");
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
}
