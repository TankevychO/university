package com.example.university_app.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    public Faculty(String name) {
        this.name = name;
    }
}
