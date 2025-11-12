package com.edusystem.eduplatform.Services;

import java.util.List;
import java.util.Optional;

public interface Course {
    Optional<com.edusystem.eduplatform.Model.Course> findById(long id);

    com.edusystem.eduplatform.Model.Course findByName(String name);

    void save(com.edusystem.eduplatform.Model.Course course);

    List<com.edusystem.eduplatform.Model.Course> findAll();

    void deletebyId(long id);

    void deletebyName(String name);

}
