package edu.aam.app.service.task;

import javax.validation.constraints.Size;

public class TaskViewModel {

    private Long id;

    private String taskName;

    @Size(min = 10, message = "Enter at least 10 Characters...")
    private String description;

    public TaskViewModel() {

    }

    public TaskViewModel(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
    }

    public TaskViewModel(Long id, String taskName, String description) {
        this.id = id;
        this.taskName = taskName;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
