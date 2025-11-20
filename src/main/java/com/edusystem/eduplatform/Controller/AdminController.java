package com.edusystem.eduplatform.Controller;

import com.edusystem.eduplatform.Exception.ResourceNotFoundException;
import com.edusystem.eduplatform.Model.User.Student;
import com.edusystem.eduplatform.Model.User.Teacher;
import com.edusystem.eduplatform.Services.StudentService;
import com.edusystem.eduplatform.Services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private StudentService studentService;
    private TeacherService teacherService;

    @Autowired
    public AdminController(StudentService studentService, TeacherService teacherService) {
        this.studentService = studentService;
        this.teacherService = teacherService;
    }

    @DeleteMapping("/student/{id}")
    String deleteStudent(@PathVariable Integer id) throws ResourceNotFoundException {
        Student student = studentService.getStudentById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        studentService.deleteStudent(student);
        return "student id's number " + id + " sucessfully delted";
    }

    @DeleteMapping("/teacher/{id}")
    String deleteTeacher(@PathVariable Long id) throws ResourceNotFoundException {
        Teacher teacher = teacherService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        teacherService.delete(teacher);
        return "teacher id's number " + id + " sucessfully delted";
    }

}
