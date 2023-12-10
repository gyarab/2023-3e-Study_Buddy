package com.example.socialnisitprostudenty.Student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Interface, který zajišťuje práci s databází uživatelů
 * */
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.email = ?1")
    Optional<Student> findStudentsByEmail(String email);

    @Transactional
    @Modifying
    @Query("UPDATE Student a " + " SET a.enabled= TRUE WHERE a.email = ?1")
    int enableStudent(String email);
}
