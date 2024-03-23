package com.example.studybuddy.articleSubject;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Sloužby spojené s předměty
 * */
@Service
@AllArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;


    /**
     * Vrátí všechny předměty
     * */
    public List<Subject> getAllSubjects(){
        return subjectRepository.findAll();
    }

    /**
     * Vrátí předmět dle is
     */
    public List<Subject> getSubjectById(Long id) {

        return (List<Subject>) subjectRepository.findById(id).orElseThrow(() -> new IllegalStateException("není zde předmět s id "+ id));
    }
}
