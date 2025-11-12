package com.edusystem.eduplatform.Services;

import com.edusystem.eduplatform.Repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class CourseImpl implements Course{


    CourseRepo courseRepo;
    @Autowired
    public CourseImpl(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }
    @Override
    public Optional<com.edusystem.eduplatform.Model.Course> findById(long id) {
        return courseRepo.findById(id);
    }

    @Override
    public com.edusystem.eduplatform.Model.Course findByName(String name) {
        return courseRepo.findByName(name);
    }

    @Override
    public void save(com.edusystem.eduplatform.Model.Course course) {
        courseRepo.save(course);
    }

    @Override
    public List<com.edusystem.eduplatform.Model.Course> findAll() {
        return courseRepo.findAll();
    }


    @Override
    public void deletebyId(long id) {
        courseRepo.deleteById(id);
    }

    @Override
    public void deletebyName(String name) {
        com.edusystem.eduplatform.Model.Course course = courseRepo.findByName(name);
        courseRepo.delete(course);
    }

}
