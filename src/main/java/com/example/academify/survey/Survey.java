package com.example.academify.survey;

import jakarta.persistence.*;

@Entity
@Table(name = "surveys")
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "question")
    private String question;

    // Standart getter ve setter metotları
    // ...
}

