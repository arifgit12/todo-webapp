package edu.aam.app.service.task;

import edu.aam.app.model.Task;
import edu.aam.app.repository.TaskRepository;
import edu.aam.app.repository.UserRepository;
import edu.aam.app.util.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService implements ITaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        task.setUser(userRepository.findByEmail(AuthenticatedUser.findLoggedInUsername()));
        Task saveTask = taskRepository.saveAndFlush(task);
        return saveTask;
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.getOne(id);
    }

    @Override
    public Task getTaskByUserName(Long id, String username) {
        Task task = taskRepository.findTaskById(id, username);
        return task;
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
    public TaskViewModel mapTaskViewModel(Long taskId, String username) {
        Task task = taskRepository.findTaskById(taskId, username);
        if(task != null) {
            TaskViewModel taskViewModel = new TaskViewModel(task.getId(), task.getTaskName(), task.getDescription());
            return taskViewModel;
        }
        return null;
    }
}
