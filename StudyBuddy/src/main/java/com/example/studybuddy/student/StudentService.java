package com.example.studybuddy.student;

import com.example.studybuddy.email.EmailSender;
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
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
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
    private final EmailSender emailSender;


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
    @Transactional
    public void updateStudentUsename(String email, String name) {
        Student student = studentRepository.findStudentsByEmail(email).orElseThrow(() -> new IllegalStateException("student s emailem " + email + " neexistuje"));

        if (name != null && student.nameChars(name) && !Objects.equals(student.getName(), name)) {
            student.setName(name);
        }
    }

    /**
     * Metoda na změnéní jména nebo hesla
     * */
    @Transactional
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

    public String signUpUser(Student student){
        boolean userExists = studentRepository.findStudentsByEmail(student.getEmail()).isPresent();

        if (userExists) {
            if(!student.getEnabled()){
                throw new IllegalStateException("email již zabrán");
            }
            // TODO: Smazat staré informace o studentovy a dát nové
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

//        TODO: POSLAT EMAIL

        return token;
    }

    public int enableAppUser(String email) {
        return studentRepository.enableStudent(email);
    }

    private String buildEmail(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
