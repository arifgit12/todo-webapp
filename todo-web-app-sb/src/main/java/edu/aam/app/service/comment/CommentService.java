package edu.aam.app.service.comment;

import edu.aam.app.model.Comment;
import edu.aam.app.model.Todo;
import edu.aam.app.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Date;
import java.util.List;

@Service
public class CommentService implements ICommentService {

    @Autowired
    CommentRepository commentRepository;

    public StringBuilder validation(BindingResult bindingResult) {
        List<ObjectError> list = bindingResult.getAllErrors();
        StringBuilder msg = new StringBuilder();
        for (ObjectError error : list)
            msg.append(error.getDefaultMessage()).append("\n");
        return msg;
    }

    @Override
    public CommentViewModel postComment(Todo todo, CommentViewModel commentViewModel) {
        Comment comment = new Comment();
        comment.setContent(commentViewModel.getContent());
        comment.setCreatedDate(new Date());
        todo.addComment(comment);
        commentRepository.save(comment);
        commentViewModel.readCommentInfo(comment);
        return commentViewModel;
    }

    @Override
    public void putComment(Long id, String comment) {
        Comment persistComment = commentRepository.getOne(id);
        persistComment.update(comment);
        commentRepository.save(persistComment);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
