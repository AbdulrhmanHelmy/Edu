package com.edusystem.eduplatform.Model;

import com.edusystem.eduplatform.Model.User.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @ManyToOne()
    private Level level;

    @OneToOne
    private Subject subject;



    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lesson> lessonList;



}
