package com.example.university_app.dao;

import com.example.university_app.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectDao extends JpaRepository<Subject, Long> {
}
