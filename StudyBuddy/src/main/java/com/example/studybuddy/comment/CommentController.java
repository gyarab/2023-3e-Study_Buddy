package com.example.studybuddy.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * Příkaz na přidání komentáře
     */
    @PostMapping
    public void addNewComment(@RequestBody Comment comment) {
        commentService.addNewComment(comment);
    }

    /**
     * Příkaz na smazání komentáře
     */
    @DeleteMapping(path = "{commentId}")
    public void delateComment(@PathVariable("commentId") Long commentId){
        commentService.delateComment(commentId);
    }
}
