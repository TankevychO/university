package com.example.university_app.service;

import java.util.List;
import com.example.university_app.model.Subject;

public interface SubjectService {
    Subject add(Subject subject);

    List<Subject> getAll();

    Subject update(Long id, Subject subject);

    void delete(Long id);
}
