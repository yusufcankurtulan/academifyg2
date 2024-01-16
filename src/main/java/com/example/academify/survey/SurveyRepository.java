package com.example.academify.survey;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    // Özel sorgular gerekirse buraya eklenebilir.
}

