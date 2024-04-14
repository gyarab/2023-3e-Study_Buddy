package com.example.studybuddy.student;

import com.example.studybuddy.email.EmailSender;
import com.example.studybuddy.registration.token.ConfirmationTokenRepository;
import com.example.studybuddy.validators.EmailValidator;
import com.example.studybuddy.registration.token.ConfirmationToken;
import com.example.studybuddy.registration.token.ConfirmationTokenService;
import com.example.studybuddy.validators.IdValidator;
import com.example.studybuddy.validators.NameValidator;
import com.example.studybuddy.validators.PasswordValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

/**
 * Třída dělající příkazy
 */
@Service
@AllArgsConstructor
public class StudentService implements UserDetailsService {

    private final static String USER_NOT_FOUND = "User with email with %s not found.";
    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private final NameValidator nameValidator;
    private final IdValidator idValidator;
    private final ConfirmationTokenRepository confirmationTokenRepository;


    /**
     * Metoda, která vrací všechny studenty z databáze.
     */
    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    /**
     * Metoda, která přidá nového studenta.
     * */
    public void addNewStudent(Student student) {

        emailValidator.test(student);
        nameValidator.test(student);
        passwordValidator.test(student);

        String encodedPassword = bCryptPasswordEncoder
                .encode(student.getPassword());

        student.setPassword(encodedPassword);

        studentRepository.save(student);
    }

    /**
     * Metoda, která smaže studenta z databáze podle id.
     * */
    public void delateStudent(Long studentId) {

        idValidator.testStudent(studentId);

        studentRepository.deleteById(studentId);
    }

    /**
     * Metoda na změnéní jména nebo hesla
     * */
    public void updateStudentUsename(String email, String name) {
        Student student = studentRepository.findStudentsByEmail(email).orElseThrow(() -> new IllegalStateException("student s emailem " + email + " neexistuje"));

        if (name != null && student.nameChars(name) && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
    }

    /**
     * Metoda na změnéní jména nebo hesla
     * */
    public void updateStudentPassword(String email, String oldPassword,String newPassword) {
        Student student = studentRepository.findStudentsByEmail(email).orElseThrow(() -> new IllegalStateException("student s emailem " + email + " neexistuje"));

        if(bCryptPasswordEncoder.matches(oldPassword, student.getPassword())){
            if (newPassword != null && student.passwordChars(newPassword) && !Objects.equals(student.getPassword(), newPassword)) {
                String encodedPassword = bCryptPasswordEncoder.encode(newPassword);

                student.setPassword(encodedPassword);
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return studentRepository.findStudentsByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND)));
    }

    /**
     *  Přidá nového uživatele a pak pošle email
     * */
    public String signUpUser(Student student){
        boolean userExists = studentRepository.findStudentsByEmail(student.getEmail()).isPresent();

        if (userExists) {
            if(studentRepository.getStudentsByEmail(student.getEmail()).getEnabled()){
                throw new IllegalStateException("email již zabrán");
            }
            Student studentInfo = studentRepository.getStudentsByEmail(student.getEmail());

            confirmationTokenService.deleteToken(confirmationTokenRepository.findTokenByStudentEmail(student.getEmail()).getId());

            delateStudent(studentInfo.getId());

            String encodedPassword = bCryptPasswordEncoder
                    .encode(student.getPassword());

            student.setPassword(encodedPassword);

            studentRepository.save(student);

        }  else {

            String encodedPassword = bCryptPasswordEncoder
                    .encode(student.getPassword());

            student.setPassword(encodedPassword);

            studentRepository.save(student);
        }

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15), student);

        confirmationTokenService.saveConfirmationToken(
                confirmationToken);

        return token;
    }

    public int enableAppUser(String email) {
        return studentRepository.enableStudent(email);
    }
}
