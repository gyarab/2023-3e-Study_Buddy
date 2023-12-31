package com.example.socialnisitprostudenty.registration.token;

import com.example.socialnisitprostudenty.student.Student;
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
public class ConfirmationToken {
    @Id
    @SequenceGenerator(name = "confirmation_token_sequence",sequenceName = "confirmation_token_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "confirmation_token_sequence")
    private Long id;
    @Column(nullable = false)
    private String token;
    @Column(nullable = false)
    private LocalDateTime createdat;
    @Column(nullable = false)
    private LocalDateTime expiresat;
    private LocalDateTime confirmedat;

    @ManyToOne
    @JoinColumn(nullable = false, name = "student_id")
    private Student student;

    public ConfirmationToken(String token, LocalDateTime createdat, LocalDateTime expiresat, Student student) {
        this.token = token;
        this.createdat = createdat;
        this.expiresat = expiresat;
        this.student = student;
    }
}
