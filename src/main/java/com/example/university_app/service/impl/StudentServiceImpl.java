package com.example.university_app.service.impl;

import java.util.List;
import java.util.Optional;
import com.example.university_app.model.Student;
import com.example.university_app.service.StudentService;
import com.example.university_app.dao.StudentDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao;

    @Override
    public Student add(Student student) {
        return studentDao.save(student);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.findAll();
    }

    @Override
    public Student update(Long id, Student student) {
        return studentDao.save(new Student(id, student.getEmail(),
                student.getPassword(), student.getGroup()));
    }

    @Override
    public void delete(Long id) {
        studentDao.deleteById(id);
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        return studentDao.findByEmail(email);
    }
}
