package com.example.university_app.service;

import java.util.List;
import com.example.university_app.dao.FacultyDao;
import com.example.university_app.model.Faculty;
import com.example.university_app.service.impl.FacultyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class FacultyServiceTest {
    @Autowired
    private FacultyDao facultyDao;
    private FacultyService facultyService;
    private Faculty t1;

    @BeforeEach
    void setUp() {
        facultyService = new FacultyServiceImpl(facultyDao);
        t1 = new Faculty("T1");
        facultyService.add(t1);
    }

    @Test
    void add_Ok() {
        Assertions.assertEquals(1, facultyDao.count());
        Assertions.assertEquals(t1, facultyDao.getById(t1.getId()));
    }

    @Test
    void update_Ok() {
        facultyService.update(t1.getId(), new Faculty("new"));
        Assertions.assertEquals(1, facultyDao.count());
        Assertions.assertEquals("new", facultyDao.getById(t1.getId()).getName());
    }

    @Test
    void getAll_Ok() {
        Faculty t2 = new Faculty("t2");
        facultyService.add(t2);
        Faculty t3 = new Faculty("t3");
        facultyService.add(t3);
        List<Faculty> expected = List.of(t1, t2, t3);
        List<Faculty> actual = facultyService.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void delete_Ok() {
        facultyService.delete(t1.getId());
        Assertions.assertEquals(0, facultyDao.count());
    }
}
