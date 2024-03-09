package com.example.studybuddy.validators;

import com.example.studybuddy.student.Student;
import org.springframework.stereotype.Service;


/**
 * Validátor hesla
 * */
@Service
public class PasswordValidator {

    public void test(Student student) {

        if (!student.passwordChars()) {
            throw new IllegalStateException("moc krátné heslo");
        }
    }
}
