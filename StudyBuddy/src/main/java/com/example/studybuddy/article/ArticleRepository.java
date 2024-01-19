package com.example.studybuddy.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Interface, který zajišťuje práci s databází článků a hledání daných prvhů
 * */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

    @Query("SELECT a FROM Article a WHERE a.title = ?1")
    Optional<Article> findArticlesByTitle(String title);

    @Query("SELECT a FROM Article a WHERE a.article = ?1")
    Optional<Article> findArticlesByArticle(String article);

    @Query("SELECT a FROM Article a WHERE a.autor = ?1")
    List<Article> findArticlesByAutor(String autor);

    @Query("SELECT a FROM Article a WHERE a.date = ?1")
    List<Article> findArticlesByDate(LocalDate date);


    @Query("SELECT a FROM Article a WHERE a.subject = ?1")
    List<Article> findArticlesBySubject(String subject);
}