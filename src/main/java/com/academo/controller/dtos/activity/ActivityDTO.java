package com.academo.controller.dtos.activity;

import java.time.LocalDate;

public record ActivityDTO(
        Integer id,
        LocalDate date,
        String name,
        String description,
        String subjectName,
        String ActivityType
) {}
