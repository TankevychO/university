package com.example.university_app.service;

import java.util.List;
import java.util.Optional;
import com.example.university_app.model.Student;

public interface StudentService {
    Student add(Student student);

    List<Student> getAll();

    Student update(Long id, Student student);

    void delete(Long id);

    Optional<Student> findByEmail(String email);
}
