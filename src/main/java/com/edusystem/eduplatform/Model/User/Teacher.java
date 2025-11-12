package com.edusystem.eduplatform.Model.User;

import com.edusystem.eduplatform.Model.Course;
import com.edusystem.eduplatform.secure.notes.Models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    private String userName;

    @OneToMany(mappedBy = "teacher")
    private List<Course> courses = new ArrayList<>();
}
