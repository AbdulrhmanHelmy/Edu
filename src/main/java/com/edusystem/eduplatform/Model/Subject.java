package com.edusystem.eduplatform.Model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Subject {
    @Id
    @GeneratedValue( strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;

}
