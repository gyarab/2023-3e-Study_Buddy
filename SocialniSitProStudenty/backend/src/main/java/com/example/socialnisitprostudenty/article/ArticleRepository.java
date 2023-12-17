package com.example.socialnisitprostudenty.article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface, který zajišťuje práci s databází článků
 * */
@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>{

    @Query("SELECT a FROM Article a WHERE a.title = ?1")
    Optional<Article> findArticlesByTitle(String title);
}