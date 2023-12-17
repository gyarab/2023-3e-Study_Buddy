package com.example.socialnisitprostudenty.student;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * třída student
 * */
@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Student implements UserDetails {
    @Id
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    private Long id;
    private String name;
    private  String email;
    private String password;
    private Long[] articles;
    @Enumerated(EnumType.STRING)
    private StudentRole studentRole;
    private Boolean locked = false;
    private Boolean enabled = true;// pro testování bez email ověřování se musí dát na true


    /**
    * Konstriktory
    * */
    public Student(Long id, String name, String email, String password, StudentRole studentRole) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.studentRole = studentRole;
    }

    public Student(String name, String email, String password, StudentRole studentRole) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.studentRole = studentRole;
    }

    public Student(String name, String email, String password, Long[] articles, StudentRole studentRole) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.articles = articles;
        this.studentRole = studentRole;
    }

    /**
     * Metoda k vypsání všech atribut ze třídy
     * */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", articles='" + Arrays.toString(articles) + '\'' +
                '}';
    }

    /**
     * zkontroluje zda proměná může být email
     * */
    public boolean isEmail(String email){
        return email.contains("@") && email.length() >= 6 && email.contains(".");
    }


    public boolean isEmail(){
        return email.contains("@") && email.length() >= 6 && email.contains(".");
    }


    /**
     * Zkontroluje zda je v hesle dostatečný počet znaků
     * */
    public boolean passwordChars(String password){
        return password.length() > 5;
    }

    public boolean passwordChars(){
        return password.length() > 5;
    }


    /**
     * Zkoumá zda je ve jméni dostatečný počet znaků
     * */
    public boolean nameChars(String name){
        return name.length() > 2;
    }

    public boolean nameChars(){
        return name.length() > 2;
    }


    /**
     * vrátí počet článků, které napsal daný uživatel
     * */
    public int numberOfArticles(){
        return articles.length;
    }


    /**
     * Metody přepisované z interface UserDetails
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(studentRole.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
