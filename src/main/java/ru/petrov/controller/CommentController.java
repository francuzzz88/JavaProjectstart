package ru.petrov.controller;


import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.petrov.domain.Comment;
import ru.petrov.domain.User;
import ru.petrov.domain.Views;
import ru.petrov.service.CommentService;

@RestController
@RequestMapping("comment")
public class CommentController {
    private final CommentService commentService;


    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;


    }

    @PostMapping
    @JsonView(Views.FullComment.class)
    public Comment create(
            @RequestBody Comment comment,
            @AuthenticationPrincipal User user
    ){

        return commentService.create(comment, user);
    }
}
