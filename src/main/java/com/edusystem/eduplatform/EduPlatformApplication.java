package com.edusystem.eduplatform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {
        "com.edusystem.eduplatform.Model",
        "com.edusystem.eduplatform.secure.notes.Models"
})
@EnableJpaRepositories(basePackages = {
        "com.edusystem.eduplatform.Repository",
        "com.edusystem.eduplatform.secure.notes.Repo"
})


public class EduPlatformApplication {
    public static void main(String[] args) {
        SpringApplication.run(EduPlatformApplication.class, args);
    }
}
