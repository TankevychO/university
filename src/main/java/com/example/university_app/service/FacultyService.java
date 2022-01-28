package com.example.university_app.service;

import java.util.List;
import com.example.university_app.model.Faculty;

public interface FacultyService {
    Faculty add(Faculty faculty);

    List<Faculty> getAll();

    Faculty update(Long id, Faculty faculty);

    void delete(Long id);
}
