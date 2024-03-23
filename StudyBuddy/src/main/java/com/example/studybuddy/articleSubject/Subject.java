package com.example.studybuddy.articleSubject;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "subject")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Subject {
    @Id
    @SequenceGenerator(name = "subject_sequence", sequenceName = "subject_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_sequence")
    private Long id;
    @Column(name = "name")
    private String name;

    Subject(String name){
        this.name = name;
    }
}

