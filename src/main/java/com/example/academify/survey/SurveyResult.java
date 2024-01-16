package com.example.academify.survey;

import jakarta.persistence.*;


@Entity
@Table(name = "survey_results")
public class SurveyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    @Column(name = "yes_count")
    private Integer yesCount = 0;

    @Column(name = "no_count")
    private Integer noCount = 0;

    // Standart getter ve setter metotları
    // ...
}

