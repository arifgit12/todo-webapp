package edu.aam.app.service.task;

import edu.aam.app.model.Task;

import java.util.List;

public interface ITaskService {
    List<Task> getAllTasks();
    Task save(Task task);
    Task getTaskById(Long id);
    Task getTaskByUserName(Long id, String username);
    Task putTask(Long id, TaskViewModel taskViewModel);
    TaskViewModel mapTaskViewModel(Long taskId);
    TaskViewModel mapTaskViewModel(Long taskId, String username);
}
