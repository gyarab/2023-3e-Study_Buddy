package com.example.studybuddy.comment;

import com.example.studybuddy.validators.IdValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Třída dělající komentářů
 */
@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final IdValidator idValidator;


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
        commentRepository.save(comment);
    }

    /**
     * Metoda, která smaže článek z databáze podle id.
     * */
    public void delateComment(Long commentId) {

        idValidator.testComment(commentId);

        commentRepository.deleteById(commentId);
    }
}
