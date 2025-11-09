package com.edusystem.eduplatform.secure.notes.Controllers;

import com.edusystem.eduplatform.secure.notes.DTO.LoginRequest;
import com.edusystem.eduplatform.secure.notes.DTO.SignupRequest;
import com.edusystem.eduplatform.secure.notes.Models.User;
import com.edusystem.eduplatform.secure.notes.Models.Role;
import com.edusystem.eduplatform.secure.notes.Models.enums.Roles;
import com.edusystem.eduplatform.secure.notes.Repo.RoleRepo;
import com.edusystem.eduplatform.secure.notes.Repo.UserRepo;
import com.edusystem.eduplatform.secure.notes.Security.Services.UserDetailsImpl;
import com.edusystem.eduplatform.secure.notes.Security.Util.JwtUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
    private RoleRepo roleRepository;

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


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {

        if(userRepository.existsByUserName(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already in use!");
        }

        // إنشاء مستخدم جديد
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                passwordEncoder.encode(signUpRequest.getPassword()));

        // إعداد الدور
        Role userRole = roleRepository.findByRoleName(Roles.STUDENT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        user.setRole(userRole);

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }
}
