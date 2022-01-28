package com.example.university_app.service;

import java.util.List;
import com.example.university_app.model.Group;

public interface GroupService {
    Group add(Group group);

    List<Group> getAll();

    Group update(Long id, Group group);

    void delete(Long id);
}
