package edu.aam.app.service.comment;

import edu.aam.app.model.Todo;

public interface ICommentService {
    CommentViewModel postComment(Todo todo, CommentViewModel comment);
    void putComment(Long id, String comment);
    void deleteComment(Long id);
}
