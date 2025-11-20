package com.edusystem.eduplatform.Services;

import com.edusystem.eduplatform.Model.User.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student saveStudent(Student student);
    Optional<Student> getStudentById(int id);
    List<Student> getAllStudents();
    void deleteStudent(Student student);
}
