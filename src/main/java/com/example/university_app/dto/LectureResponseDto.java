package com.example.university_app.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LectureResponseDto {
    private Long id;
    private String subjectName;
    private LocalDate date;
    private String lectureHallName;
    private List<String> groupsNames;
}
