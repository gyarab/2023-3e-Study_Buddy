package com.example.studybuddy.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Interface, který zajišťuje práci s databází komentářů
 * */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.article = ?1")
    List<Comment> findCommentsByArticle(Long article);
}
