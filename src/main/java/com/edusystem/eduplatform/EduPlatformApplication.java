package com.edusystem.eduplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("com.edusystem.eduplatform.secure.notes.Repo") // تأكد من الباكج الصحيح
@EntityScan("com.edusystem.eduplatform.secure.notes.Models")        // Entities
public class EduPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduPlatformApplication.class, args);
    }
}
