package com.example.university_app.dao;

import java.util.Optional;
import com.example.university_app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentDao extends JpaRepository<Student, Long> {
    Optional<Student> findByEmail(String email);
}
