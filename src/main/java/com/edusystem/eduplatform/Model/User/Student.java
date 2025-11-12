package com.edusystem.eduplatform.Model.User;

import com.edusystem.eduplatform.Model.Course;
import com.edusystem.eduplatform.Model.Lesson;
import com.edusystem.eduplatform.secure.notes.Models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

    @OneToMany
    private List<Course> courses;

    @OneToMany // محتاج ريفرس قبل ما نرجعه
    private List<Lesson> history;


    private Double averageScore;
    private Integer completedCoursesCount;
    private Double progressPercentage;


    private Long totalWatchTime;
    private Long points;
    private Long streakDays;


}
