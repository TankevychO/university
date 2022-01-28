package com.example.university_app.service.impl;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import com.example.university_app.model.Lecture;
import com.example.university_app.model.Student;
import com.example.university_app.service.LectureService;
import com.example.university_app.dao.LectureDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final LectureDao lectureDao;

    @Override
    public Lecture add(Lecture lecture) {
        return lectureDao.save(lecture);
    }

    @Override
    public List<Lecture> getAll() {
        return lectureDao.findAll();
    }

    @Override
    public Lecture update(Long id, Lecture lecture) {
        Lecture newLecture = lectureDao.getById(id);
        newLecture.setLectureHall(lecture.getLectureHall());
        newLecture.setDate(lecture.getDate());
        newLecture.setGroups(new HashSet<>(lecture.getGroups()));
        newLecture.setSubject(lecture.getSubject());
        return lectureDao.saveAndFlush(newLecture);
    }

    @Override
    public void delete(Long id) {
        lectureDao.deleteById(id);
    }

    @Override
    public List<Lecture> findByStudentAndDate(Student student, LocalDate date) {
        return lectureDao.findByStudentAndDate(student.getId(), date);
    }
}
