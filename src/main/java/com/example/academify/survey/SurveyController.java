package com.example.academify.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    // Anket sonuçlarını kaydetme endpoint'i
    @PostMapping("/submit")
    public ResponseEntity<String> submitSurvey(@RequestBody SurveyResult surveyResult) {
        surveyService.saveSurveyResult(surveyResult);
        return ResponseEntity.ok("Anket sonucu başarıyla kaydedildi.");
    }

    // Öğretmene ait anket sonuçlarını getirme endpoint'i
    @GetMapping("/results/{teacherName}")
    public ResponseEntity<List<SurveyResult>> getSurveyResults(@PathVariable String teacherName) {
        List<SurveyResult> surveyResults = surveyService.getSurveyResultsForTeacher(teacherName);
        return ResponseEntity.ok(surveyResults);
    }

    // Öğretmene ait anket sonuçlarının ortalamalarını getirme endpoint'i
    // ...
}

