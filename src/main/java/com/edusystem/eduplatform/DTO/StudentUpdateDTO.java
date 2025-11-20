package com.edusystem.eduplatform.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentUpdateDTO {
    private String userName;

    private Double averageScore;
    private Integer completedCoursesCount;
    private Double progressPercentage;
    private Double balance;
    private Long totalWatchTime;
    private Long points;
    private Long streakDays;
}
