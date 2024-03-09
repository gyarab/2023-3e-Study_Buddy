package com.example.studybuddy.article;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Table(name = "article")
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
    @Column(name = "autor")
    private Long autor;
    @Column(name = "title")
    private String title;
    @Column(name = "article")
    private String article;
    @Column(name = "date")
    private LocalDate date;
    @Column(name = "subject")
    private Long subject;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", autor=" + autor +
                ", title='" + title + '\'' +
                ", article='" + article + '\'' +
                ", date=" + date +
                ", subject='" + subject + '\'' +
                '}';
    }

    public Article(String title, Long autor, String article, LocalDate date, Long subject) {
        this.title = title;
        this.autor = autor;
        this.article = article;
        this.date = date;
        this.subject = subject;
    }

    public Article(String title, String article) {
        this.article = article;
        this.title = title;
        this.date = LocalDate.now();
    }
    //TODO: Musí se zjistit uživatel, kterej je přihlášen a pak ho uložit jakožto autora článku
    //TODO: Musí se naplnit databáze subject předměty a pak vytvořit přiřazování předmětů k článkům
}
