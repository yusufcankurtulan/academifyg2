package com.example.academify.survey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {
    @Query("SELECT sr FROM SurveyResult sr WHERE sr.survey.teacherName = :teacherName")
    List<SurveyResult> findByTeacherName(@Param("teacherName") String teacherName);
}
