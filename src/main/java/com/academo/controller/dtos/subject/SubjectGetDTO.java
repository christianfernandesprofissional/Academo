package com.academo.controller.dtos.subject;

import java.time.LocalDateTime;

public record SubjectGetDTO(
        Integer id,
        String name,
        String description,
        boolean isActive,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
