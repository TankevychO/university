package com.example.university_app.model;

import java.time.LocalDate;
import java.util.Set;;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Subject subject;
    private LocalDate date;
    @ManyToMany
    @JoinTable(name = "lectures_groups",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<Group> groups;
    @ManyToOne
    private LectureHall lectureHall;

    public Lecture(Subject subject, LocalDate date, Set<Group> groups, LectureHall lectureHall) {
        this.subject = subject;
        this.date = date;
        this.groups = groups;
        this.lectureHall = lectureHall;
    }
}
