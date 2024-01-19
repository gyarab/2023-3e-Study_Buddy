package com.example.studybuddy.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Třída sloužící k ovládání databáze
 */
@RestController
@RequestMapping("api/v1/student") //Tuto třídu lze najít na stránce na loalhost:8080//api/v1/student
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
     * Příkaz na přepsání jednoho ze studentů
     */
    @PutMapping(path = "{studentId}")
    public void updateStudent( @PathVariable("studentId") Long studentId, @RequestParam(required = false) String name, @RequestParam(required = false) String email, @RequestParam(required = false) String password, Long[] articles){
        studentService.updateStudent(studentId, name, email, password, articles);
    }
}
