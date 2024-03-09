package com.example.studybuddy.validators;

import com.example.studybuddy.article.Article;
import com.example.studybuddy.article.ArticleRepository;
import com.example.studybuddy.student.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Validátory jmen
 * */
@Service
@AllArgsConstructor
public class NameValidator {

    private final ArticleRepository articleRepository;

    public void test(Student student) {

        if (!student.nameChars()) {
            throw new IllegalStateException("moc krátné jmeno");
        }
    }

    public void test(Article article) {

        Optional<Article> articleOptional = articleRepository.findArticlesByTitle(article.getTitle());

        if (articleOptional.isPresent()) {
            throw new IllegalStateException("Název již existuje. Zvolte jiný.");
        }
    }
}
