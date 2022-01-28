package com.example.university_app.service.impl;

import java.util.List;
import com.example.university_app.model.LectureHall;
import com.example.university_app.service.LectureHallService;
import com.example.university_app.dao.LectureHallDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LectureHallServiceImpl implements LectureHallService {
    private final LectureHallDao lectureHallDao;

    @Override
    public LectureHall add(LectureHall lectureHall) {
        return lectureHallDao.save(lectureHall);
    }

    @Override
    public List<LectureHall> getAll() {
        return lectureHallDao.findAll();
    }

    @Override
    public LectureHall update(Long id, LectureHall lectureHall) {
        return lectureHallDao.save(new LectureHall(id, lectureHall.getName()));
    }

    @Override
    public void delete(Long id) {
        lectureHallDao.deleteById(id);
    }
}
