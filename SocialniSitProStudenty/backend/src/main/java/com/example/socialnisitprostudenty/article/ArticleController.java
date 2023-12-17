package com.example.socialnisitprostudenty.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public void addNewAtricle(@RequestBody Article article) {
        articleService.addNewArticle(article);
    }

    /**
     * Příkaz na smazání článku
     */
    @DeleteMapping(path = "{artilceId}")
    public void delateArticle(@PathVariable("artilceId") Long artilceId){
        articleService.delateArticle(artilceId);
    }

}
