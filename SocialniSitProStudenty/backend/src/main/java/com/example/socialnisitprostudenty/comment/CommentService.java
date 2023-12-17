package com.example.socialnisitprostudenty.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Třída dělající komentářů
 */
@Service
public class CommentService {


    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * Metoda, která vrací všechny články z databáze.
     */
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    /**
     * Metoda, která přidá nový článek.
     * */
    public void addNewComment(Comment comment) {
        Optional<Comment> commentOptional = commentRepository.findCommentsByArticle(comment.getArticle());
        if (commentOptional.isPresent()) {
            throw new IllegalStateException("Název již existuje. Zvolte jiný.");
        }

        commentRepository.save(comment);
    }

    /**
     * Metoda, která smaže článek z databáze podle id.
     * */
    public void delateComment(Long commentId) {
        boolean exist = commentRepository.existsById(commentId);
        if (!exist) {
            throw new IllegalStateException("student s id " + commentId + " neexistuje");
        }
        commentRepository.deleteById(commentId);
    }
}
