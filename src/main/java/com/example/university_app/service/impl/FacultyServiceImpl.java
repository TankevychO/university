package com.example.university_app.service.impl;

import java.util.List;
import com.example.university_app.model.Faculty;
import com.example.university_app.service.FacultyService;
import com.example.university_app.dao.FacultyDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FacultyServiceImpl implements FacultyService {
    private final FacultyDao facultyDao;

    @Override
    public Faculty add(Faculty faculty) {
        return facultyDao.save(faculty);
    }

    @Override
    public List<Faculty> getAll() {
        return facultyDao.findAll();
    }

    @Override
    public Faculty update(Long id, Faculty faculty) {
        return facultyDao.save(new Faculty(id, faculty.getName()));
    }

    @Override
    public void delete(Long id) {
        facultyDao.deleteById(id);
    }
}
