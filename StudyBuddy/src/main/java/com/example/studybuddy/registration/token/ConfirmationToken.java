package com.example.studybuddy.registration.token;

import com.example.studybuddy.student.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Třída slouží k vytvoření tokenu, díky kterému se pak dá polsat email
 * */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken {
    @Id
    @SequenceGenerator(name = "confirmation_token_sequence",sequenceName = "confirmation_token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_token_sequence")
    private Long id;
    @Column(name = "token", nullable = false)
    private String token;
    @Column(name = "createdate", nullable = false)
    private LocalDateTime createdate;
    @Column(name = "expiresat", nullable = false)
    private LocalDateTime expiresat;
    @Column(name = "confirmedat")
    private LocalDateTime confirmedat;

    @ManyToOne
    @JoinColumn(nullable = false, name = "student_id")
    private Student student;

    public ConfirmationToken(String token, LocalDateTime createdate, LocalDateTime expiresat, Student student) {
        this.token = token;
        this.createdate = createdate;
        this.expiresat = expiresat;
        this.student = student;
    }
}
