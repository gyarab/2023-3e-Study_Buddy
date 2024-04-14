package com.example.studybuddy.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Třída sloužící k ovládání databáze
 */
@RestController
@RequestMapping("api/v1/student") //Tuto třídu lze najít na stránce na localhost:8080/api/v1/student
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    /**
     * Příkaz na dostání studentů
     */
    @GetMapping
    public List<Student> getStudents(){
        return studentService.getStudents();
    }

    /**
     * Příkaz na přidání studenta
     */
    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    /**
     * Příkaz na smazání studenta
     */
    @DeleteMapping(path = "{studentId}")
    public void delateStudent(@PathVariable("studentId") Long studentId){
        studentService.delateStudent(studentId);
    }

    /**
     * Příkaz na přepsání hesla jednoho ze studentů
     */
    @PutMapping(path = "password")
    public void updateStudentPassword(@RequestBody PasswordChange passwordChange, Principal principal){
        studentService.updateStudentPassword(principal.getName(),passwordChange.getOldPassword(),passwordChange.getNewPassword());
    }

    /**
     * Příkaz na přepsání uživatelského jména jednoho ze studentů
     */
    @PutMapping(path = "username")
    public void updateStudentUsername(@RequestBody String newusername , Principal principal){
        studentService.updateStudentUsename(principal.getName(), newusername);
    }
}
