package com.edusystem.eduplatform.Repository;

import com.edusystem.eduplatform.Model.User.Teacher;
import com.edusystem.eduplatform.secure.notes.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    Optional<Teacher> findByUser(User user);

}
