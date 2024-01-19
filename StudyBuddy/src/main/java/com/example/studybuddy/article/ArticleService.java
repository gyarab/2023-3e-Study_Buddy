package com.example.studybuddy.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Třída dělající příkazy
 */
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    /**
     * Metoda, která vrací všechny články z databáze.
     */
    public List<Article> getArticles(){
        return articleRepository.findAll();
    }

    /**
     * Metoda, která přidá nový článek.
     * */
    public void addNewArticle(Article article) {
        Optional<Article> articleOptional = articleRepository.findArticlesByTitle(article.getTitle());
        if (articleOptional.isPresent()) {
            throw new IllegalStateException("Název již existuje. Zvolte jiný.");
        }

        articleRepository.save(article);
    }

    /**
     * Metoda, která smaže článek z databáze podle id.
     * */
    public void delateArticle(Long artilceId) {
        boolean exist = articleRepository.existsById(artilceId);
        if (!exist) {
            throw new IllegalStateException("student s id " + artilceId + " neexistuje");
        }
        articleRepository.deleteById(artilceId);
    }

}
