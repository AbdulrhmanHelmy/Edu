package com.edusystem.eduplatform.Repository;

import com.edusystem.eduplatform.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepo extends JpaRepository<Course,Long> {
//    Optional<Course> findByName(String name);

    Course findByName(String name);
}
