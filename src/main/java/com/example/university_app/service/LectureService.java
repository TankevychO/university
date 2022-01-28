package com.example.university_app.service;

import java.time.LocalDate;
import java.util.List;
import com.example.university_app.model.Lecture;
import com.example.university_app.model.Student;

public interface LectureService {
    Lecture add(Lecture lecture);

    List<Lecture> getAll();

    Lecture update(Long id, Lecture lecture);

    void delete(Long id);

    List<Lecture> findByStudentAndDate(Student student, LocalDate date);
}
