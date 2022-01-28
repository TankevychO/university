package com.example.university_app.dao;

import java.time.LocalDate;
import java.util.List;
import com.example.university_app.model.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LectureDao extends JpaRepository<Lecture,Long> {
    @Query("SELECT l FROM Lecture l, Student s "
            + "join fetch l.groups g "
            + "WHERE s.group.id = g.id and s.id = ?1 and l.date = ?2")
    List<Lecture> findByStudentAndDate(Long studentId, LocalDate date);

    @Query("SELECT l FROM Lecture l "
            + "join fetch l.groups g "
            + "WHERE l.id = ?1")
    Lecture getById(Long id);
}
