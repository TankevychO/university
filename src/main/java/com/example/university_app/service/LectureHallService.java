package com.example.university_app.service;

import java.util.List;
import com.example.university_app.model.LectureHall;

public interface LectureHallService {
    LectureHall add(LectureHall lectureHall);

    List<LectureHall> getAll();

    LectureHall update(Long id, LectureHall lectureHall);

    void delete(Long id);
}
