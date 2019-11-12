package edu.aam.app.service.todo;

import java.util.Date;

public class TodoViewModel {
    private Long taskId;
    private Long todoId;
    private String description;
    private Date targetDate;
    private Boolean status;
    private Date createdDate;
    private Date updatedDate;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
