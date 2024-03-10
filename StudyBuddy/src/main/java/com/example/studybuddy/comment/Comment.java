package com.example.studybuddy.comment;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "comment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Comment {
    @Id
    @SequenceGenerator(name = "comment_sequence", sequenceName = "comment_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_sequence")
    private Long id;
    @Column(name = "autor")
    private Long autor;
    @Column(name = "article")
    private Long article;
    @Column(name = "text")
    private String text;
    @Column(name = "date")
    private LocalDate date;

    public Comment(String comment, Long article){
        this.text = comment;
        this.article = article;
        this.date = LocalDate.now();
    }

    //TODO: musí se kompletně dodělat
}
