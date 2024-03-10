package com.example.studybuddy.comment;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Tělo nově zadaného komentáře
 * */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class CommentRequest {
    private final Long articleId;
    private final String comment;
}
