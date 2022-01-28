package com.example.university_app.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import com.example.university_app.dto.LectureResponseDto;
import com.example.university_app.model.Student;
import com.example.university_app.service.LectureService;
import com.example.university_app.service.StudentService;
import com.example.university_app.service.mapper.LectureMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/lectures")
public class LectureController {
    private final LectureService lectureService;
    private final LectureMapper lectureMapper;
    private  final StudentService studentService;

    @GetMapping
    List<LectureResponseDto> get(Authentication auth, @RequestParam LocalDate date) {
        UserDetails details = (UserDetails) auth.getPrincipal();
        String email = details.getUsername();
        Student student = studentService.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Student with email " + email + " not found"));
        return lectureService.findByStudentAndDate(student, date).stream()
                .map(lectureMapper::mapToDto)
                .collect(Collectors.toList());
    }
}
