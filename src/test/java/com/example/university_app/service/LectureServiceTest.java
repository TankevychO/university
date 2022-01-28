package com.example.university_app.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import com.example.university_app.dao.FacultyDao;
import com.example.university_app.dao.GroupDao;
import com.example.university_app.dao.LectureDao;
import com.example.university_app.dao.LectureHallDao;
import com.example.university_app.dao.SubjectDao;
import com.example.university_app.model.Faculty;
import com.example.university_app.model.Group;
import com.example.university_app.model.Lecture;
import com.example.university_app.model.LectureHall;
import com.example.university_app.model.Subject;
import com.example.university_app.service.impl.LectureServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class LectureServiceTest {
    @Autowired
    private LectureDao lectureDao;
    @Autowired
    private SubjectDao subjectDao;
    @Autowired
    private LectureHallDao lectureHallDao;
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private FacultyDao facultyDao;
    private Group t1;
    private Subject math;
    private LectureHall hall;
    private LocalDate date;
    private Faculty fait;
    private LectureService lectureService;
    private Lecture lecture;

    @BeforeEach
    void setUp() {
        lectureService = new LectureServiceImpl(lectureDao);
        date = LocalDate.of(2022,01,15);
        math = new Subject("Mathematics");
        subjectDao.save(math);
        hall = new LectureHall("#1");
        lectureHallDao.save(hall);
        fait = new Faculty("fait");
        facultyDao.save(fait);
        t1 = new Group("T1", fait, Set.of(math));
        groupDao.save(t1);
        lecture = new Lecture(math, date, Set.of(t1), hall);
        lectureService.add(lecture);
    }

    @Test
    void add_Ok() {
        Assertions.assertEquals(1, lectureDao.count());
        Assertions.assertEquals(lecture, lectureDao.getById(lecture.getId()));
    }

    @Test
    void update_Ok() {
        lectureService.update(lecture.getId(), new Lecture(math,
                LocalDate.of(2022, 02, 17),
                Set.of(t1), hall));
        Assertions.assertEquals(1, lectureDao.count());
        Assertions.assertEquals(LocalDate.of(2022, 02, 17),
                lectureDao.getById(lecture.getId()).getDate());
    }

    @Test
    void getAll_Ok() {
        Lecture lecture1 = new Lecture(math,
                LocalDate.of(2022, 01, 17), Set.of(t1), hall);
        lectureService.add(lecture1);
        List<Lecture> expected = List.of(lecture, lecture1);
        List<Lecture> actual = lectureService.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void delete_Ok() {
        lectureService.delete(lecture.getId());
        Assertions.assertEquals(0, lectureDao.count());
    }
}
