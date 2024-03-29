package com.example.studybuddy.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Třída sloužící k ovládání databáze s komentáři
 */
@RestController
@RequestMapping("api/v1/comment") //Tuto třídu lze najít na stránce na loalhost:8080//api/v1/comment
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService){
        this.commentService = commentService;
    }

    /**
     * Příkaz na dostání komentářů
     */
    @GetMapping
    public List<Comment> getComment(){
        return commentService.getComments();
    }

    /**
     * Příkaz na dostání komentářů dle id
     */
    @GetMapping(path = "{id}")
    public List<Comment> getCommentById(@PathVariable("id") Long articleId){
        return commentService.getCommentsById(articleId);
    }

    /**
     * Příkaz na přidání komentáře
     */
    @PostMapping
    public void addNewComment(@RequestBody CommentRequest commentRequest, Principal principal) {
        commentService.addNewComment(commentRequest, principal);
    }

    /**
     * Příkaz na smazání komentáře
     */
    @DeleteMapping(path = "{commentId}")
    public void deleteComment(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
    }
}
