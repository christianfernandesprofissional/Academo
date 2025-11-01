package com.academo.controller.dtos.activity;

import java.time.LocalDate;

public record ActivityDTO(
        Integer id,
        LocalDate notificationDate,
        LocalDate activityDate,
        String name,
        String description,
        String subjectName,
        String ActivityType
) {}
