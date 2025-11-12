package com.edusystem.eduplatform.Model;

import com.edusystem.eduplatform.Model.User.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long Id;

    private String title;
    private String description;
    private String videoUrl;
    private String imageUrl;

    private int lessonOrder;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
