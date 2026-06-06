package com.example.QuoraApp.Dto;

import java.time.LocalDateTime;

import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LikeResponseDto {
    private String id;
    private String targetId;
    private String targetType;
    private Boolean isLike;
    private LocalDateTime createdAt;
}
