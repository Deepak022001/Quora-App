package com.example.QuoraApp.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class QuestionResponseDto {
    private String id;
    private String title;
    private String content;
    private LocalDateTime createdAt;
}
