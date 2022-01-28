package com.example.university_app.service.impl;

import java.util.HashSet;
import java.util.List;
import com.example.university_app.model.Group;
import com.example.university_app.dao.GroupDao;
import com.example.university_app.service.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupDao groupDao;

    @Override
    public Group add(Group group) {
        return groupDao.save(group);
    }

    @Override
    public List<Group> getAll() {
        return groupDao.findAll();
    }

    @Override
    public Group update(Long id, Group group) {
        Group newGroup = groupDao.getById(id);
        newGroup.setFaculty(group.getFaculty());
        newGroup.setSubjects(new HashSet<>(group.getSubjects()));
        newGroup.setName(group.getName());
        return groupDao.saveAndFlush(newGroup);
    }

    @Override
    public void delete(Long id) {
        groupDao.deleteById(id);
    }
}
