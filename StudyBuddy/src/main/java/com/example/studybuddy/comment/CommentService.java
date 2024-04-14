package com.example.studybuddy.comment;

import com.example.studybuddy.student.StudentRepository;
import com.example.studybuddy.validators.IdValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

/**
 * Třída dělající komentářů
 */
@Service
@AllArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final IdValidator idValidator;
    private final StudentRepository studentRepository;


    /**
     * Metoda, která vrací všechny komentáře z databáze.
     */
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

    /**
     * Metoda, která přidá nový komentář.
     * */
    public void addNewComment(CommentRequest commentRequest, Principal principal) {

        Long studentId = studentRepository.findStudentsByEmail(principal.getName()).get().getId();

        Comment comment = new Comment(commentRequest.getComment(), commentRequest.getArticleId(), studentId);

        commentRepository.save(comment);
    }

    /**
     * Metoda, která smaže komentář z databáze podle id.
     * */
    public void deleteComment(Long commentId) {

        idValidator.testComment(commentId);

        commentRepository.deleteById(commentId);
    }


    /**
     * Metoda, která vrací komentáře dle id autora
     * */
    public List<Comment> getCommentsById(Long articleId) {
        return commentRepository.findCommentsByArticle(articleId);
    }
}
