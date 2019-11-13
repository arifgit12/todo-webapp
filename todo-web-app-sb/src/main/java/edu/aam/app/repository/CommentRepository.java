package edu.aam.app.repository;

import edu.aam.app.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Comment findByContent(String content);
}
