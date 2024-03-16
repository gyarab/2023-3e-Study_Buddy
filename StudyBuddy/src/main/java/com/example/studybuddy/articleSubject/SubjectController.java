package com.example.studybuddy.articleSubject;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//
//@RestController
//@RequestMapping("api/v1/subjcets")
//public class SubjectController {
//
//    private final SubjectService subjectService;
//
//    @Autowired
//    public SubjectController(SubjectService subjectService){
//        this.subjectService = subjectService;
//    }
//
//    /**
//     * Příkaz na dostání všech předmětů
//     */
//    @GetMapping
//    public List<Subject> getAllSubjects(){
//        return subjectService.getAllSubjects();
//    }
//
//    /**
//     * Příkaz na dostání předmětu dle id
//     */
//    @GetMapping(path = "/byid")
//    public List<Subject> getSubject(@RequestBody SubjectRequest subjectRequest){
//        return subjectService.getSubjectById(subjectRequest);
//    }
//}
