package com.example.QuoraApp.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class AnswerResponseDto {
    private String id;
    private String content;
    private String questionId;
    private LocalDateTime createdAt;
}
