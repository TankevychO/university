package com.example.university_app.dao;

import com.example.university_app.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupDao extends JpaRepository<Group, Long> {
    @Query("SELECT g FROM Group g "
            + "join fetch g.subjects s "
            + "WHERE g.id = ?1")
    Group getById(Long id);
}
