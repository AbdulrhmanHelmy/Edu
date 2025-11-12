package com.edusystem.eduplatform.secure.notes.Controllers;

import com.edusystem.eduplatform.Model.User.Student;
import com.edusystem.eduplatform.Model.User.Teacher;
import com.edusystem.eduplatform.Repository.StudentRepository;
import com.edusystem.eduplatform.Repository.TeacherRepository;
import com.edusystem.eduplatform.secure.notes.DTO.LoginRequest;
import com.edusystem.eduplatform.secure.notes.DTO.SignupRequest;
import com.edusystem.eduplatform.secure.notes.Models.Role;
import com.edusystem.eduplatform.secure.notes.Models.User;
import com.edusystem.eduplatform.secure.notes.Models.enums.Roles;
import com.edusystem.eduplatform.secure.notes.Repo.RoleRepo;
import com.edusystem.eduplatform.secure.notes.Repo.UserRepo;
import com.edusystem.eduplatform.secure.notes.Security.Util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    private UserRepo userRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    private RoleRepo roleRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtUtils.generateToken(loginRequest.getUsername());
            return ResponseEntity.ok(Map.of(
                    "message", "User signed in successfully",
                    "token", token
            ));
        } catch (AuthenticationException e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", "Invalid username or password"));
        }
    }

    @PostMapping("/signup/student")
    public ResponseEntity<?> registerStudent(@RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword())
        );

        Role userRole = roleRepository.findByRoleName(Roles.STUDENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.setRole(userRole);

        user = userRepository.save(user);

        Student student = new Student();
        student.setUser(user);
        student.setUserName(user.getUserName());

        studentRepository.save(student);

        return ResponseEntity.ok("student registered successfully!");
    }

    @PostMapping("/signup/teacher")
    public ResponseEntity<?> registerTeacher(@RequestBody SignupRequest signUpRequest) {

        if (userRepository.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword())
        );

        Role userRole = roleRepository.findByRoleName(Roles.TEACHER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.setRole(userRole);

        user = userRepository.save(user);


        Teacher teacher = new Teacher();
        teacher.setUser(user);
        teacher.setUserName(user.getUserName());

        teacherRepository.save(teacher);

        return ResponseEntity.ok("Teacher registered successfully!");
    }
}
