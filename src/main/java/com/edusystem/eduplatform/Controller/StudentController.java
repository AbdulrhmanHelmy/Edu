package com.edusystem.eduplatform.Controller;

import com.edusystem.eduplatform.DTO.StudentUpdateDTO;
import com.edusystem.eduplatform.Exception.ResourceNotFoundException;
import com.edusystem.eduplatform.Model.User.Student;
import com.edusystem.eduplatform.Services.StudentService;
import com.edusystem.eduplatform.secure.notes.Models.User;
import com.edusystem.eduplatform.secure.notes.Repo.UserRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {
    private StudentService studentService;
    private ObjectMapper objectMapper;
private  UserRepo userRepo;
    @Autowired
    public StudentController(StudentService studentService, ObjectMapper theObjectMapper,UserRepo theUserRepo) {
        this.studentService = studentService;
        objectMapper = theObjectMapper;

        userRepo = theUserRepo;

    }

    @PatchMapping("/{id}")
    public Student updateStudent(
            @PathVariable Integer
                    id,
            @RequestBody StudentUpdateDTO dto
    ) throws ResourceNotFoundException {

        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        if (dto.getUserName() != null) {
            User user = student.getUser();
            user.setUserName(dto.getUserName());
            userRepo.save(user);
        }

        if (dto.getAverageScore() != null) student.setAverageScore(dto.getAverageScore());
        if (dto.getCompletedCoursesCount() != null) student.setCompletedCoursesCount(dto.getCompletedCoursesCount());
        if (dto.getProgressPercentage() != null) student.setProgressPercentage(dto.getProgressPercentage());
        if (dto.getBalance() != null) student.setBalance(dto.getBalance());
        if (dto.getTotalWatchTime() != null) student.setTotalWatchTime(dto.getTotalWatchTime());
        if (dto.getPoints() != null) student.setPoints(dto.getPoints());
        if (dto.getStreakDays() != null) student.setStreakDays(dto.getStreakDays());

        return studentService.saveStudent(student);
    }

}
