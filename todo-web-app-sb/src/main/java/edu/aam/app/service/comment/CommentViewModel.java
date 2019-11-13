package edu.aam.app.service.comment;

import edu.aam.app.model.Comment;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class CommentViewModel {

    @NotBlank
    @Length(min = 1, max = 10, message = "Comment 1-255 characters.")
    private String content;

    private Integer todoId;

    private Long commentId;

    private Date dateTime;

    private Date modifyTime;

    public void readCommentInfo(Comment comment) {
        this.commentId = comment.getId();
        this.content = comment.getContent();
        this.dateTime = comment.getCreatedDate();
        this.modifyTime = comment.getModifiedDate();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getTodoId() {
        return todoId;
    }

    public void setTodoId(Integer todoId) {
        this.todoId = todoId;
    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
