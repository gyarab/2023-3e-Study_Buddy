package com.example.studybuddy.student;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * parametry pro změnu hesla
 * */
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class PasswordChange {
    private final String oldPassword;
    private final String newPassword;
}