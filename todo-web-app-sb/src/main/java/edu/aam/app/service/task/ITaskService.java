package edu.aam.app.service.task;

import edu.aam.app.model.Task;

import java.util.List;

public interface ITaskService {
    List<Task> getAllTasks();
    Task save(Task task);
    Task getTaskById(Long id);
    Task putTask(Long id, TaskViewModel taskViewModel);
    TaskViewModel mapTaskViewModel(Long taskId);
}
