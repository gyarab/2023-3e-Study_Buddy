package com.example.studybuddy.articleSubject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SubjectConfig {

    @Bean
    CommandLineRunner commandLineRunner(SubjectRepository repository) {
        return args -> {
            Subject english = new Subject(
                    "English Language"
            );
            Subject math = new Subject(
                    "Mathematics"
            );
            Subject language = new Subject(
                    "Other Language"
            );
            Subject biologic = new Subject(
                    "Biologic"
            );
            Subject geography = new Subject(
                    "Geography"
            );
            Subject music = new Subject(
                    "Music"
            );
            Subject art = new Subject(
                    "Art"
            );
            Subject history = new Subject(
                    "History"
            );
            Subject chemistry = new Subject(
                    "Chemistry"
            );
            Subject physics = new Subject(
                    "Physics"
            );
            Subject other = new Subject(
                    "Other"
            );
            repository.saveAll(
                    List.of(english,math,language,biologic,geography,music,art,history,chemistry,physics,other)
            );
        };
    }
}
