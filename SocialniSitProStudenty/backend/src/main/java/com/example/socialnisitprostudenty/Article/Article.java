package com.example.socialnisitprostudenty.Article;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "Article")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Article {
    @Id
    @SequenceGenerator(name = "article_sequence", sequenceName = "article_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_sequence")
    private Long id;
    private Long autor;
    private String title;
    private String article;
    private int likes;
    private int dislikes;
    private Long[] coments;
    private LocalDate date;
    private String subject;

    /**
     * Konstruktory
     * */
    public Article(String title, Long autor, String article, int likes, int dislikes, Long[] coments, LocalDate date, String subject) {
        this.title = title;
        this.autor = autor;
        this.article = article;
        this.likes = likes;
        this.dislikes = dislikes;
        this.coments = coments;
        this.date = date;
        this.subject = subject;
    }

    public Article(Long id, Long autor, String title, String article, int likes, int dislikes, LocalDate date, String subject) {
        this.id = id;
        this.autor = autor;
        this.title = title;
        this.article = article;
        this.likes = likes;
        this.dislikes = dislikes;
        this.date = date;
        this.subject = subject;
    }

    public Article(String title, Long autor, String article, int likes, int dislikes, LocalDate date, String subject) {
        this.title = title;
        this.autor = autor;
        this.article = article;
        this.likes = likes;
        this.dislikes = dislikes;
        this.date = date;
        this.subject = subject;
    }

    public Article(Long id, Long autor, String title, String article, Long[] coments, LocalDate date, String subject) {
        this.id = id;
        this.autor = autor;
        this.title = title;
        this.article = article;
        this.likes = 0;
        this.dislikes = 0;
        this.coments = coments;
        this.date = date;
        this.subject = subject;
    }

    public Article(String title, Long autor, String article, Long[] coments, LocalDate date, String subject) {
        this.title = title;
        this.autor = autor;
        this.article = article;
        this.likes = 0;
        this.dislikes = 0;
        this.coments = coments;
        this.date = date;
        this.subject = subject;
    }

    public Article(Long id, Long autor, String title, String article, LocalDate date, String subject) {
        this.id = id;
        this.autor = autor;
        this.title = title;
        this.article = article;
        this.likes = 0;
        this.dislikes = 0;
        this.date = date;
        this.subject = subject;
    }

    public Article(String title, Long autor, String article, LocalDate date, String subject) {
        this.title = title;
        this.autor = autor;
        this.article = article;
        this.likes = 0;
        this.dislikes = 0;
        this.date = date;
        this.subject = subject;
    }
}
