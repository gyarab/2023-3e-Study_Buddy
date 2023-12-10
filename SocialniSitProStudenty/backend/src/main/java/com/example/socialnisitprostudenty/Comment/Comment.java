package com.example.socialnisitprostudenty.Comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    private Long id;
    private Long autor;
    private Long article;
    private String text;
    private int likes;
    private int dislikes;
    private LocalDate date;
}
