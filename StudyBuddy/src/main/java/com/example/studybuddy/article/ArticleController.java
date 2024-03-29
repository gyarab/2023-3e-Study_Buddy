package com.example.studybuddy.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Třída sloužící k ovládání databáze s příspěvky
 */
@RestController
@RequestMapping("api/v1/article") //Tuto třídu lze najít na stránce na loalhost:8080//api/v1/article
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    /**
     * Příkaz na dostání článku
     */
    @GetMapping
    public List<Article> getArticle(){
        return articleService.getArticles();
    }

    /**
     * Příkaz na přidání článku
     */
    @PostMapping
    public void addNewArticle(@RequestBody ArticleRequest article, Principal principal) {
        articleService.addNewArticle(article, principal.getName());
    }

    /**
     * Příkaz na smazání článku
     */
    @DeleteMapping(path = "{articleId}")
    public void deleteArticle(@PathVariable Long articleId){
        articleService.delateArticle(articleId);
    }

    /**
     * Příkaz na dostání článků přihlášeného autora
     */
    @GetMapping(value = "/autor")
    public List<Article> getArticlesByAutor(Principal principal){
        return articleService.getArticlesByAutor(principal.getName());
    }

    /**
     * Příkaz na dostání článků přihlášeného autora
     */
    @GetMapping(value = "{subject}")
    public List<Article> getArticlesBySubject(@PathVariable Long subject){
        return articleService.getArticlesBySubject(subject);
    }
}
