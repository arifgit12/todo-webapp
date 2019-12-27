package edu.aam.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "todos")
public class Todo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Size(min = 10, message = "Enter at least 10 Characters...")
	private String description;

	private Date targetDate;

	private Boolean status;

	private Date createdDate;

	private Date updatedDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id")
	private Task taskList;

	@OneToMany(mappedBy = "toDo", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonIgnore
	List<Comment> comments = new ArrayList<>();

	public Todo() {
		super();
	}

	public Todo(String desc, Date targetDate, boolean isDone) {
		super();
		this.description = desc;
		this.targetDate = targetDate;
		this.status = isDone;
	}

	public void addComment(Comment comment) {
		comment.setToDo(this);
		this.comments.add(comment);
	}

	public void StatusUpdate(boolean bls) {
		this.status = !bls;
		this.updatedDate = this.status ? new Date() : null;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Todo{" +
				"id=" + id +
				", description='" + description + '\'' +
				", targetDate=" + targetDate +
				", status=" + status +
				", task Name=" + taskList.getTaskName() +
				", comments size=" + comments.size() +
				'}';
	}
}