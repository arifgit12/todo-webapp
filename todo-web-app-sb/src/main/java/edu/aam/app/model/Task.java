package edu.aam.app.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String taskName;

    @Column
    @Size(min = 10, message = "Enter at least 10 Characters...")
    private String description;

    @OneToMany(mappedBy = "taskList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Todo> todoList = new ArrayList<>();

    private String userName;

    public Task() {

    }

    public Task(String taskName, @Size(min = 10, message = "Enter at least 10 Characters...") String description) {
        this.taskName = taskName;
        this.description = description;
    }

    public Task(String taskName, @Size(min = 10, message = "Enter at least 10 Characters...") String description, String userName) {
        this.taskName = taskName;
        this.description = description;
        this.userName = userName;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public List<Todo> getTodoList() {
        return todoList;
    }

    public void setTodoList(List<Todo> todoList) {
        this.todoList = todoList;
    }
}
