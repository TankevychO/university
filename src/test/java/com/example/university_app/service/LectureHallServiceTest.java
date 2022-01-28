package com.example.university_app.service;

import java.util.List;
import com.example.university_app.dao.LectureHallDao;
import com.example.university_app.model.LectureHall;
import com.example.university_app.service.impl.LectureHallServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class LectureHallServiceTest {
    @Autowired
    private LectureHallDao lectureHallDao;
    private LectureHallService lectureHallService;
    private  LectureHall lectureHall;

    @BeforeEach
    void setUp() {
        lectureHallService = new LectureHallServiceImpl(lectureHallDao);
        lectureHall = new LectureHall("#1");
        lectureHallService.add(lectureHall);
    }

    @Test
    void add_Ok() {
        Assertions.assertEquals(1, lectureHallDao.count());
        Assertions.assertEquals(lectureHall, lectureHallDao.getById(lectureHall.getId()));
    }

    @Test
    void update_Ok() {
        lectureHallService.update(lectureHall.getId(), new LectureHall("#2"));
        Assertions.assertEquals(1, lectureHallDao.count());
        Assertions.assertEquals("#2", lectureHallDao.getById(lectureHall.getId()).getName());
    }

    @Test
    void getAll_Ok() {
        LectureHall lectureHall2 = new LectureHall("#2");
        lectureHallService.add(lectureHall2);;
        LectureHall lectureHall3 = new LectureHall("#3");
        lectureHallService.add(lectureHall3);
        List<LectureHall> expected = List.of(lectureHall, lectureHall2, lectureHall3);
        List<LectureHall> actual = lectureHallService.getAll();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void delete_Ok() {
        lectureHallService.delete(lectureHall.getId());
        Assertions.assertEquals(0, lectureHallDao.count());
    }
}
