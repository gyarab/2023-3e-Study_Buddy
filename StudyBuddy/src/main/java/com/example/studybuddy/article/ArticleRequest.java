package com.example.studybuddy.article;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Tělo nově zadaného článku
 * */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class ArticleRequest {
    private final String title;
    private final String article;
}
