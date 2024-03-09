package com.example.studybuddy.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
    public void addNewAtricle(@RequestBody ArticleRequest article) {
        articleService.addNewArticle(article);
    }

    /**
     * Příkaz na smazání článku
     */
    @DeleteMapping(path = "{artilceId}")
    public void delateArticle(@PathVariable("artilceId") Long artilceId){
        articleService.delateArticle(artilceId);
    }

    /*
    musí se dodělat aby jmeno autora, datum, nebo předmět brala aplikace z cesty
    * */

    /**
     * Příkaz na dostání článků autora, jehož příspěvek chceme vidět
     */
    @GetMapping(value = "/autor")
    public List<Article> getArticlesByAutor(String autor){
        return articleService.getArticlesByAutor(autor);
    }

    /**
     * Příkaz na dostání článku dle předmětu
     */
    @GetMapping(value = "/subject")
    public List<Article> getArticlesBySubject(String subject){return articleService.getArticlesBySubject(subject);}

    /**
     * Příkaz na dostání článku z uvedeného data
     */
    @GetMapping(value = "/date")
    public List<Article> getArticlesByDate(LocalDate date){return articleService.getArticlesByDate(date);}

}
