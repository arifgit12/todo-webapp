package edu.aam.app.service.task;

import edu.aam.app.model.Task;
import edu.aam.app.model.User;
import edu.aam.app.repository.TaskRepository;
import edu.aam.app.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.powermock.reflect.Whitebox;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceTest {

    private ITaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserRepository userRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        taskService = new TaskService();
        Whitebox.setInternalState(taskService, "taskRepository", taskRepository);
        Whitebox.setInternalState(taskService, "userRepository", userRepository);
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
    public void getTaskByUserNameTest() {
        when(taskRepository.findTaskById(0L, "testmail@gmail.com")).thenReturn(null);
        Task result = taskService.getTaskByUserName(0L, "testmail@gmail.com");
        assertNull(result);
    }

    @Test
    public void getTaskByUserNameTest1() {
        Task task = setTask();
        when(taskRepository.findTaskById(0L, "notmail@gmail.com")).thenReturn(task);
        Task result = taskService.getTaskByUserName(0L, "notmail@gmail.com");
        assertNotNull(result);
        assertNotEquals(task.getUser().getEmail(), "notmail@gmail.com");
    }

    @Test
    public void getTaskByUserNameTest2() {
        Task task = setTask();
        when(taskRepository.findTaskById(0L, "testmail@gmail.com")).thenReturn(task);
        Task result = taskService.getTaskByUserName(0L, "testmail@gmail.com");
        assertNotNull(result);
        assertEquals(task.getUser().getEmail(), "testmail@gmail.com");
    }

    @Test
    public void getAllTasksTest() {
        Task task1 = new Task();
        task1.setTaskName("task1");
        Task task2 = new Task();
        task2.setTaskName("task2");

        List<Task> stubResults = Arrays.asList(task1, task2);
        when(taskRepository.findAll()).thenReturn(stubResults);
        List<Task> results = taskService.getAllTasks();
        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    public void saveTestFail() {

        TaskViewModel taskViewModel = new TaskViewModel();
        taskViewModel.setTaskName("task-Name");
        taskViewModel.setDescription("task description");

        when(userRepository.findByEmail(anyString())).thenReturn(setUser());
        when(taskRepository.saveAndFlush(any())).thenReturn(setTask());

        Task result = taskService.save(taskViewModel, "testmail@gmail.com");
        assertNotNull(result);
        assertNotEquals(taskViewModel.getTaskName(), result.getTaskName());
    }

    @Test
    public void saveTestSuccess() {

        TaskViewModel taskViewModel = new TaskViewModel();
        taskViewModel.setTaskName("task1");
        taskViewModel.setDescription("task description");

        when(userRepository.findByEmail(anyString())).thenReturn(setUser());
        when(taskRepository.saveAndFlush(any())).thenReturn(setTask());

        Task result = taskService.save(taskViewModel, "testmail@gmail.com");
        assertNotNull(result);
        assertEquals(taskViewModel.getTaskName(), result.getTaskName());
    }
}
