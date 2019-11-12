package edu.aam.app.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todos")
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String userName;

	@Size(min = 10, message = "Enter at least 10 Characters...")
	private String description;

	private Date targetDate;

	private Boolean status;

	private Date createdDate;

	private Date updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	private Task taskList;

	@OneToMany(mappedBy = "toDo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Comment> comments = new ArrayList<>();

	public Todo() {
		super();
	}

	public Todo(String user, String desc, Date targetDate, boolean isDone) {
		super();
		this.userName = user;
		this.description = desc;
		this.targetDate = targetDate;
		this.status = isDone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Task getTaskList() {
		return taskList;
	}

	public void setTaskList(Task taskList) {
		this.taskList = taskList;
	}
}