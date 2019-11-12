package edu.aam.app.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment implements Comparable<Comment> {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    @Length(min = 1, max = 255, message = "Comments 1-255.")
    private String content;

    @Column
    private Boolean status;

    @Column(nullable = false)
    private Date createdDate;

    @Column
    private Date modifiedDate;

    @Column
    private Date completedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Todo toDo;

    public Comment(String content, Boolean status, Date createdDate, Date modifiedDate, Date completedDate, Todo toDo) {
        this.content = content;
        this.status = status;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.completedDate = completedDate;
        this.toDo = toDo;
    }

    public void StatusUpdate(boolean bls) {
        this.status = !bls;
        this.completedDate = this.status ? new Date() : null;
    }

    public void update(String comment) {
        this.content = comment;
        this.modifiedDate = new Date();
    }

    @Override
    public int compareTo(Comment o) {
        return this.id < o.id ? -1 : 1;
    }
}
