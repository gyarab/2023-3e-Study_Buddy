package com.example.studybuddy.subject;

import lombok.*;

import javax.persistence.*;


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
    Long id;
    @Column(name = "name")
    String name;
}

