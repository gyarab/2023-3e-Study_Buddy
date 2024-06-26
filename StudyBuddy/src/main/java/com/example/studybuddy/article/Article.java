package com.example.studybuddy.article;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


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
    @Column(name = "article", length = 1000000)
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

    public Article(String title, String article, Long autor, Long subject) {
        this.article = article;
        this.title = title;
        this.date = LocalDate.now();
        this.autor = autor;
        this.subject = subject;
    }
}
