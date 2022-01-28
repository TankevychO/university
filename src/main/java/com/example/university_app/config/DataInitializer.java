package com.example.university_app.config;

import java.time.LocalDate;
import java.util.Set;
import javax.annotation.PostConstruct;
import com.example.university_app.model.Faculty;
import com.example.university_app.model.Group;
import com.example.university_app.model.Lecture;
import com.example.university_app.model.LectureHall;
import com.example.university_app.model.Student;
import com.example.university_app.model.Subject;
import com.example.university_app.service.FacultyService;
import com.example.university_app.service.GroupService;
import com.example.university_app.service.LectureHallService;
import com.example.university_app.service.LectureService;
import com.example.university_app.service.StudentService;
import com.example.university_app.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer {
    private final StudentService studentService;
    private final GroupService groupService;
    private final FacultyService facultyService;
    private final SubjectService subjectService;
    private final LectureHallService lectureHallService;
    private final LectureService lectureService;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void inject() {
        Faculty ait = new Faculty("ait");
        facultyService.add(ait);
        Faculty pcm = new Faculty("pcm");
        facultyService.add(pcm);

        Subject math = new Subject("Mathematics");
        subjectService.add(math);
        Subject eng = new Subject("English");
        subjectService.add(eng);
        Subject physic = new Subject("Physic");
        subjectService.add(physic);

        Group t1 = new Group("T1", ait, Set.of(math, physic));
        groupService.add(t1);
        Group l1 = new Group("L1", pcm, Set.of(eng, math));
        groupService.add(l1);

        Student petrov = new Student("petrov@gmail.com", passwordEncoder.encode("1234"), t1);
        studentService.add(petrov);
        Student vasuk = new Student("vasuk@gmail.com", passwordEncoder.encode("1234"), t1);
        studentService.add(vasuk);
        Student litvin = new Student("litvin@gmail.com", passwordEncoder.encode("1234"), t1);
        studentService.add(litvin);
        Student horeva = new Student("horeva@gmail.com", passwordEncoder.encode("1234"), l1);
        studentService.add(horeva);
        Student samoilov = new Student("samoilov@gmail.com", passwordEncoder.encode("1234"), l1);
        studentService.add(samoilov);
        Student ivanov = new Student("ivanov@gmail.com", passwordEncoder.encode("1234"), l1);
        studentService.add(ivanov);

        LectureHall hall1 = new LectureHall("#1");
        lectureHallService.add(hall1);
        LectureHall hall2 = new LectureHall("#2");
        lectureHallService.add(hall2);
        LectureHall hall3 = new LectureHall("#3");
        lectureHallService.add(hall3);

        Lecture lecture1 = new Lecture(math, LocalDate.of(2022, 01, 15), Set.of(t1, l1), hall1);
        lectureService.add(lecture1);
        Lecture lecture2 = new Lecture(eng, LocalDate.of(2022, 01, 15), Set.of(l1), hall2);
        lectureService.add(lecture2);
        Lecture lecture3 = new Lecture(physic, LocalDate.of(2022, 01, 15), Set.of(t1), hall3);
        lectureService.add(lecture3);
        Lecture lecture4 = new Lecture(physic, LocalDate.of(2022, 01, 16), Set.of(t1), hall3);
        lectureService.add(lecture4);
        Lecture lecture5 = new Lecture(eng, LocalDate.of(2022, 01, 15), Set.of(l1), hall2);
        lectureService.add(lecture5);
    }
}
