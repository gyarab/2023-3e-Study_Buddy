package com.example.studybuddy.article;

import com.example.studybuddy.validators.IdValidator;
import com.example.studybuddy.validators.NameValidator;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    /**
     * Metoda, která vrací všechny články z databáze.
     */
    public List<Article> getArticles(){
        return articleRepository.findAll();
    }

    /**
     * Metoda, která přidá nový článek.
     * */
    public void addNewArticle(ArticleRequest articleRequest) {

        Article article = new Article(articleRequest.getTitle(), articleRequest.getArticle());

        nameValidator.test(article);

        articleRepository.save(article);
    }

    /**
     * Metoda, která smaže článek z databáze podle id.
     * */
    public void delateArticle(Long artilceId) {

        idValidator.testArticle(artilceId);

        articleRepository.deleteById(artilceId);
    }

    /**
     * Metoda, která vrací všechny články od daného autora z databáze.
     */
    public List<Article> getArticlesByAutor(String autor) {
        return articleRepository.findArticlesByAutor(autor);
    }

    /**
     * Metoda, která vrací všechny články z jednoho předmětu z databáze.
     */
    public List<Article> getArticlesBySubject(String subject) {
        return articleRepository.findArticlesBySubject(subject);
    }

    /**
     * Metoda, která vrací všechny články z daného data z databáze.
     */
    public List<Article> getArticlesByDate(LocalDate date) {
        return articleRepository.findArticlesByDate(date);
    }
}
