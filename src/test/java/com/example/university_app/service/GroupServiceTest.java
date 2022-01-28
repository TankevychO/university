package com.example.university_app.service;

import java.util.List;
import java.util.Set;
import com.example.university_app.dao.FacultyDao;
import com.example.university_app.dao.GroupDao;
import com.example.university_app.dao.SubjectDao;
import com.example.university_app.model.Faculty;
import com.example.university_app.model.Group;
import com.example.university_app.model.Subject;
import com.example.university_app.service.impl.GroupServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class GroupServiceTest {
    @Autowired
    private GroupDao groupDao;
    @Autowired
    private FacultyDao facultyDao;
    @Autowired
    private SubjectDao subjectDao;
    private GroupService groupService;
    private Faculty fau;
    private Subject math;
    private Group  t1;

    @BeforeEach
    void setUp() {
        groupService = new GroupServiceImpl(groupDao);
        fau = new Faculty("FAU");
        facultyDao.save(fau);
        math = new Subject("Mathematics");
        subjectDao.save(math);
        t1 = new Group("T1", fau, Set.of(math));
        groupService.add(t1);
    }

    @Test
    void add_Ok() {
        Assertions.assertEquals(1, groupDao.count());
        Assertions.assertEquals(t1, groupDao.getById(t1.getId()));
    }

    @Test
    void update_Ok() {;
        Group t2 = new Group("T2", fau, Set.of(math));
        groupService.update(t1.getId(), t2);
        Assertions.assertEquals(1, groupDao.count());
        Assertions.assertEquals("T2", groupDao.getById(t1.getId()).getName());
    }

    @Test
    void getAll_Ok() {
        Group t2 = new Group("T2", fau, Set.of(math));
        groupService.add(t2);
        Group t3 = new Group("T3", fau, Set.of(math));
        groupService.add(t3);
        List<Group> expected = List.of(t1, t2, t3);
        List<Group> actual = groupService.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void delete_Ok() {
        groupService.delete(t1.getId());
        Assertions.assertEquals(0, groupDao.count());
    }
}
