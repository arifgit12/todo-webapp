package edu.aam.app.controller;

import edu.aam.app.model.Todo;
import edu.aam.app.repository.TodoRepository;
import edu.aam.app.service.comment.CommentService;
import edu.aam.app.service.comment.CommentViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    CommentService commentService;

    @Autowired
    TodoRepository todoRepository;

    @PostMapping
    public ResponseEntity<?> postComment(@RequestBody @Valid CommentViewModel commentDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder msg = commentService.validation(bindingResult);
            return new ResponseEntity<>(msg.toString(), HttpStatus.BAD_REQUEST);
        }

        Todo todo = todoRepository.getOne(commentDTO.getTodoId());
        commentDTO = commentService.postComment(todo, commentDTO);
        return new ResponseEntity<>(commentDTO, HttpStatus.CREATED);
    }
}
