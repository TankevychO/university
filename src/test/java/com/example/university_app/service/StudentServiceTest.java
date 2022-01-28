package com.example.university_app.service;

import java.util.List;
import java.util.Set;
import com.example.university_app.dao.FacultyDao;
import com.example.university_app.dao.GroupDao;
import com.example.university_app.dao.StudentDao;
import com.example.university_app.dao.SubjectDao;
import com.example.university_app.model.Faculty;
import com.example.university_app.model.Group;
import com.example.university_app.model.Student;
import com.example.university_app.model.Subject;
import com.example.university_app.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentServiceTest {
        @Autowired
        private SubjectDao subjectDao;
        @Autowired
        private GroupDao groupDao;
        @Autowired
        private FacultyDao facultyDao;
        @Autowired
        private StudentDao studentDao;
        private StudentService studentService;
        private Student petrov;
        private Group t1;
        private Subject math;
        private Faculty fait;

        @BeforeEach
        void setUp() {
                studentService = new StudentServiceImpl(studentDao);
                math = new Subject("Mathematics");
                subjectDao.save(math);
                fait = new Faculty("fait");
                facultyDao.save(fait);
                t1 = new Group("T1", fait, Set.of(math));
                groupDao.save(t1);
                petrov = new Student("petrov@gmail.com", "1234", t1);
                studentService.add(petrov);
        }

        @Test
        void add_Ok() {
                Assertions.assertEquals(1, studentDao.count());
                Assertions.assertEquals(petrov, studentDao.getById(petrov.getId()));
        }

        @Test
        void update_Ok() {
                studentService.update(petrov.getId(),
                        new Student("ivanov@gmail.com", "1234", t1));
                Assertions.assertEquals(1, studentDao.count());
                Assertions.assertEquals("ivanov@gmail.com",
                        studentDao.getById(petrov.getId()).getEmail());
        }

        @Test
        void getAll_Ok() {
                Student ivanov = new Student("ivanov@gmail.com", "1234", t1);
                studentService.add(ivanov);
                List<Student> expected = List.of(petrov, ivanov);
                List<Student> actual = studentService.getAll();
                Assertions.assertEquals(expected, actual);
        }

        @Test
        void delete_Ok() {
                studentService.delete(petrov.getId());
                Assertions.assertEquals(0, studentDao.count());
        }
}
