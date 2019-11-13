package edu.aam.app.service.task;

import edu.aam.app.model.Task;
import edu.aam.app.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        Task saveTask = taskRepository.save(task);
        return saveTask;
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getOne(id);
    }

    @Override
    public Task putTask(Long id, TaskViewModel taskViewModel) {
        Task task = taskRepository.getOne(id);
        if(task == null) {
            return null;
        }
        task.setTaskName(taskViewModel.getTaskName());
        task.setDescription(taskViewModel.getDescription());

        Task saveTask = taskRepository.save(task);
        return saveTask;
    }

    @Override
    public TaskViewModel mapTaskViewModel(Long taskId) {
        Task task = taskRepository.getOne(taskId);
        TaskViewModel taskViewModel = new TaskViewModel(task.getId(), task.getTaskName(), task.getDescription());
        return taskViewModel;
    }
}
