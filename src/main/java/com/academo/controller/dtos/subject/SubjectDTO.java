package com.academo.controller.dtos.subject;

public record SubjectDTO(
        Integer id,
        String name,
        String description,
        boolean isActive
) {
}
