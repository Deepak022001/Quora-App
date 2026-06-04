package com.example.QuoraApp.adapter;

import com.example.QuoraApp.Dto.QuestionResponseDto;
import com.example.QuoraApp.models.Question;

public class QuestionAdapter {
    public static QuestionResponseDto toQuestionResponseDto(Question question){
        return QuestionResponseDto
        .builder()
        .id(question.getId())
        .title(question.getTitle())
        .content(question.getContent())
        .createdAt(question.getCreatedAt())
        .build();
    }
}
