package com.example.university_app.service.mapper;

import java.util.stream.Collectors;
import com.example.university_app.dto.LectureResponseDto;
import com.example.university_app.model.Group;
import com.example.university_app.model.Lecture;
import org.springframework.stereotype.Component;

@Component
public class LectureMapper {
    public LectureResponseDto mapToDto(Lecture lecture) {
        LectureResponseDto dto = new LectureResponseDto();
        dto.setId(lecture.getId());
        dto.setDate(lecture.getDate());
        dto.setGroupsNames(lecture.getGroups().stream().map(Group::getName).collect(Collectors.toList()));
        dto.setLectureHallName(lecture.getLectureHall().getName());
        dto.setSubjectName(lecture.getSubject().getName());
        return dto;
    }
}
