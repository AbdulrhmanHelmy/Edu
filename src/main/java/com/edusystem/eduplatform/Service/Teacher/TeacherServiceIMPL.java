package com.edusystem.eduplatform.Service.Teacher;

import com.edusystem.eduplatform.Exception.ResourceNotFoundException;
import com.edusystem.eduplatform.Model.Course;
import com.edusystem.eduplatform.Model.Lesson;
import com.edusystem.eduplatform.Model.User.Teacher;
import com.edusystem.eduplatform.Repository.TeacherRepository;
import com.edusystem.eduplatform.secure.notes.Models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceIMPL implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceIMPL(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }
    @Override
    public Teacher findByUser(User user) throws ResourceNotFoundException {
        return teacherRepository.findByUser(user).orElseThrow(
                ()->new ResourceNotFoundException("Teacher not found")
        );
    }

    @Override
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(Teacher teacher) {
        return null;
    }

    @Override
    public void delete(Teacher teacher) {

    }

    @Override
    public List<Teacher> findAll() {
        return List.of();
    }

    @Override
    public void addCourse(Course course) {

    }

    @Override
    public void removeCourse(Course course) {

    }

    @Override
    public void addLesson(Lesson lesson, Course course) {

    }

    @Override
    public void removeLesson(Lesson lesson, Course course) {

    }

    @Override
    public void deleteLesson(Lesson lesson, Course course) {

    }

    @Override
    public void deleteCourse(Course course) {

    }
}
