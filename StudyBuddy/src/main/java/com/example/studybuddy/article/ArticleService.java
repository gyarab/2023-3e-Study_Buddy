package com.example.studybuddy.article;

import com.example.studybuddy.comment.Comment;
import com.example.studybuddy.comment.CommentRepository;
import com.example.studybuddy.comment.CommentService;
import com.example.studybuddy.student.Student;
import com.example.studybuddy.student.StudentRepository;
import com.example.studybuddy.validators.IdValidator;
import com.example.studybuddy.validators.NameValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Třída dělající příkazy
 */
@Service
@AllArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final NameValidator nameValidator;
    private final IdValidator idValidator;
    private final StudentRepository studentRepository;
    private final CommentRepository commentRepository;
    private final CommentService commentService;

    /**
     * Metoda, která vrací všechny články z databáze.
     */
    public List<Article> getArticles(){
        return articleRepository.findAll();
    }

    /**
     * Metoda, která přidá nový článek.
     * */
    public void addNewArticle(ArticleRequest articleRequest, String email) {
        Student student = studentRepository.findStudentsByEmail(email).orElseThrow(() -> new IllegalStateException("student s emailem " + email + " neexistuje"));

        Article article = new Article(articleRequest.getTitle(), articleRequest.getArticle(), student.getId(), articleRequest.getArticleSubject());

        nameValidator.test(article);

        articleRepository.save(article);
    }

    /**
     * Metoda, která smaže článek a komentáře, které jsou pod článkem, z databáze podle id
     * */
    public void delateArticle(Long artilceId) {

        idValidator.testArticle(artilceId);

        List<Comment> comment = commentRepository.findCommentsByArticle(artilceId);

        for(Comment c: comment){
            commentService.deleteComment(c.getId());
        }

        articleRepository.deleteById(artilceId);
    }

    /**
     * Metoda, která vrací všechny články od daného autora z databáze.
     */
    public List<Article> getArticlesByAutor(String autor) {

        Student student = studentRepository.findStudentsByEmail(autor).orElseThrow(() -> new IllegalStateException("student s emailem " + autor + " neexistuje"));
        return articleRepository.findArticlesByAutor(student.getId());
    }

    /**
     * Vrátí všechny články s daným předmětem
     * */
    public List<Article> getArticlesBySubject(Long subject) {
        return articleRepository.findArticlesBySubject(subject);
    }
}
