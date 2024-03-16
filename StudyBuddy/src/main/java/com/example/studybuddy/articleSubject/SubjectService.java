//package com.example.studybuddy.articleSubject;
//
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
///**
// * Sloužby spojené s předměty
// * */
//@Service
//@AllArgsConstructor
//public class SubjectService {
//
//    private final SubjectRepository subjectRepository;
//
//
//    /**
//     * Vrátí všechny předměty
//     * */
//    public List<Subject> getAllSubjects(){
//        return subjectRepository.findAll();
//    }
//
//    /**
//     * Vrátí předmět dle is
//     */
//    public List<Subject> getSubjectById(SubjectRequest subjectRequest) {
//
//        return (List<Subject>) subjectRepository.findById(subjectRequest.getSubjectId()).orElseThrow(() -> new IllegalStateException("není zde předmět s id "+ subjectRequest.getSubjectId()));
//    }
//}
