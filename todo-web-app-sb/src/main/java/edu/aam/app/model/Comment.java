package edu.aam.app.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment implements Comparable<Comment>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Length(min = 1, max = 255, message = "Comments 1-255.")
    private String content;

    @Column(nullable = false)
    private Date createdDate;

    @Column
    private Date modifiedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "todo_id")
    private Todo toDo;

    public Comment() {

    }

    public Comment(String content, Date createdDate, Date modifiedDate, Todo toDo) {
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.toDo = toDo;
    }

    public void update(String comment) {
        this.content = comment;
        this.modifiedDate = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Todo getToDo() {
        return toDo;
    }

    public void setToDo(Todo toDo) {
        this.toDo = toDo;
    }

    @Override
    public int compareTo(Comment o) {
        return this.id < o.id ? -1 : 1;
    }
}
