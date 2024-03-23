package com.example.studybuddy.articleSubject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/subjects")
public class SubjectController {

    private final SubjectService subjectService;

    @Autowired
    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    /**
     * Příkaz na dostání všech předmětů
     */
    @GetMapping
    public List<Subject> getAllSubjects(){
        return subjectService.getAllSubjects();
    }

    /**
     * Příkaz na dostání předmětu dle id
     */
    @GetMapping(path = "{id}")
    public List<Subject> getSubject(@PathVariable Long id){
        return subjectService.getSubjectById(id);
    }
}
