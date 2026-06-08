package com.example.QuoraApp.models;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Likes")
public class Like {
    @Id
    private String id;

    private String targetId;

    private String targetType; // Question,answer

    private Boolean isLike;

    @CreatedDate
    private LocalDateTime createdAt;
}
