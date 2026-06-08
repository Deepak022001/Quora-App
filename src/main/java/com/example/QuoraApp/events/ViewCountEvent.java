package com.example.QuoraApp.events;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ViewCountEvent {
    private String targetId;

    private String targetType;

    private LocalDateTime timestamp;
}
