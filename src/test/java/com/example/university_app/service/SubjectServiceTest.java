package com.example.university_app.service;

import java.util.List;
import com.example.university_app.dao.SubjectDao;
import com.example.university_app.model.Subject;
import com.example.university_app.service.impl.SubjectServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class SubjectServiceTest {
    @Autowired
    private SubjectDao subjectDao;
    private SubjectService subjectService;
    private Subject math;

    @BeforeEach
    void setUp() {
        subjectService = new SubjectServiceImpl(subjectDao);
        math = new Subject("Mathematics");
    }

    @Test
    void add_Ok() {
        subjectService.add(math);
        Assertions.assertEquals(1, subjectDao.count());
        Assertions.assertEquals(math, subjectDao.getById(math.getId()));
    }

    @Test
    void update_Ok() {
        subjectService.add(math);
        subjectService.update(math.getId(), new Subject("English"));
        Assertions.assertEquals(1, subjectDao.count());
        Assertions.assertEquals("English", subjectDao.getById(math.getId()).getName());
    }

    @Test
    void getAll_Ok() {
        subjectService.add(math);
        Subject eng = new Subject("English");
        subjectService.add(eng);
        Subject chem = new Subject("Chemistry");
        subjectService.add(chem);
        List<Subject> expected = List.of(math, eng, chem);
        List<Subject> actual = subjectService.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void delete_Ok() {
        subjectService.add(math);
        subjectService.delete(math.getId());
        Assertions.assertEquals(0, subjectDao.count());
    }
}
