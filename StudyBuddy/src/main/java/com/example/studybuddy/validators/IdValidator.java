package com.example.studybuddy.validators;

import com.example.studybuddy.article.ArticleRepository;
import com.example.studybuddy.comment.CommentRepository;
import com.example.studybuddy.student.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Validátory Id
 * */
@Service
@AllArgsConstructor
public class IdValidator {

    private final StudentRepository studentRepository;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    public void testStudent(Long studentId) {
        boolean exist = studentRepository.existsById(studentId);
        if (!exist) {
            throw new IllegalStateException("student s id " + studentId + " neexistuje");
        }
    }

    public void testArticle(Long artilceId) {
        boolean exist = articleRepository.existsById(artilceId);
        if (!exist) {
            throw new IllegalStateException("student s id " + artilceId + " neexistuje");
        }
    }

    public void testComment(Long commentId) {
        boolean exist = commentRepository.existsById(commentId);
        if (!exist) {
            throw new IllegalStateException("student s id " + commentId + " neexistuje");
        }
    }
}
