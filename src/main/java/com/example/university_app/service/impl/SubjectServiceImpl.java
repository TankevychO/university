package com.example.university_app.service.impl;

import java.util.List;
import com.example.university_app.model.Subject;
import com.example.university_app.dao.SubjectDao;
import com.example.university_app.service.SubjectService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    private final SubjectDao subjectDao;

    @Override
    public Subject add(Subject subject) {
        return subjectDao.save(subject);
    }

    @Override
    public List<Subject> getAll() {
        return subjectDao.findAll();
    }

    @Override
    public Subject update(Long id, Subject subject) {
        return subjectDao.save(new Subject(id, subject.getName()));
    }

    @Override
    public void delete(Long id) {
        subjectDao.deleteById(id);
    }
}
