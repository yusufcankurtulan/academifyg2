package com.example.academify.survey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private SurveyResultRepository surveyResultRepository;

    // Anket sonucu kaydetme metodu
    public void saveSurveyResult(SurveyResult surveyResult) {
        surveyResultRepository.save(surveyResult);
    }

    // Öğretmene ait anket sonuçlarını getirme metodu
    public List<SurveyResult> getSurveyResultsForTeacher(String teacherName) {
        return surveyResultRepository.findByTeacherName(teacherName);
    }

    // Anket sonuçlarının ortalamalarını hesaplama metodu
    // ...
}

