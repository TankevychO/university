package com.example.university_app.dao;

import com.example.university_app.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacultyDao extends JpaRepository<Faculty, Long> {
}
