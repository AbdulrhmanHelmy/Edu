package com.edusystem.eduplatform.Services;

import com.edusystem.eduplatform.Exception.ResourceNotFoundException;
import com.edusystem.eduplatform.Model.Course;
import com.edusystem.eduplatform.Model.Lesson;
import com.edusystem.eduplatform.Model.User.Teacher;
import com.edusystem.eduplatform.secure.notes.Models.User;
import java.util.List;
import java.util.Optional;

public interface TeacherService {
    Teacher findByUser(User user) throws ResourceNotFoundException;
    Teacher save(Teacher teacher);
    Optional<Teacher> findById(Long id) ;
    Teacher update(Teacher teacher);
    void delete(Teacher teacher);
    List<Teacher> findAll();
    void addCourse(Course course );
    void removeCourse(Course course);
    void addLesson(Lesson lesson,Course course);
    void removeLesson(Lesson lesson ,Course course);
    void deleteLesson(Lesson lesson,Course course);
    void deleteCourse(Course course);

}
