package com.edusystem.eduplatform.Services;

import com.edusystem.eduplatform.Model.User.Student;
import com.edusystem.eduplatform.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service

public class StudentServiceimpl implements StudentService {
   private StudentRepository studentRepository;
   public StudentServiceimpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
   }


    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudent(Student student) {
         studentRepository.delete(student);
    }
}
