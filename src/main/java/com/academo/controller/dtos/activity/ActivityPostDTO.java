package com.academo.controller.dtos.activity;

import java.time.LocalDate;

public record ActivityPostDTO(
        LocalDate date,
        String name,
        String description,
        Integer subjectId,
        Integer activityTypeId
) {}
