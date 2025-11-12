package com.edusystem.eduplatform.Repository;

import com.edusystem.eduplatform.Model.User.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
