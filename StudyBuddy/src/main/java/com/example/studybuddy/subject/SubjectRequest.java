package com.example.studybuddy.subject;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * tělo požadavku pro předmět
 * */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class SubjectRequest {
    private final Long subjectId;
}
