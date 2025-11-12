package com.edusystem.eduplatform.Model.User;

import com.edusystem.eduplatform.Model.Course;
import com.edusystem.eduplatform.Model.Lesson;
import com.edusystem.eduplatform.secure.notes.Models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private User user;

    @ManyToMany
    @JoinTable(
            name = "student_course", // اسم الجدول الوسيط
            joinColumns = @JoinColumn(name = "student_id"), // العمود الذي يشير للطالب
            inverseJoinColumns = @JoinColumn(name = "course_id") // العمود الذي يشير للكورس
    )
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Lesson> history = new ArrayList<>();

    private String userName  ;
    private Double averageScore = 0.0;
    private Integer completedCoursesCount = 0;
    private Double progressPercentage = 0.0;


    private Long totalWatchTime = (long) 0;
    private Long points = (long) 0;
    private Long streakDays = (long) 0;


}


