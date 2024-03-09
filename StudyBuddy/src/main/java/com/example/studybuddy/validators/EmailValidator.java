package com.example.studybuddy.validators;

import com.example.studybuddy.student.Student;
import com.example.studybuddy.student.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Validátor emailu
 * */
@Service
@AllArgsConstructor
public class EmailValidator  {

    private final StudentRepository studentRepository;
    public void test(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentsByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("email je zabraný");
        }

        if(!student.isEmail()){
            throw new IllegalStateException("email neexistuje");
        }
    }
}
