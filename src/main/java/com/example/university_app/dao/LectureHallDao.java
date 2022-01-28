package com.example.university_app.dao;

import com.example.university_app.model.LectureHall;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureHallDao extends JpaRepository<LectureHall, Long> {
}
