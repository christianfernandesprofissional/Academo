package com.academo.controller.dtos.activity;

import java.time.LocalDate;

public record ActivityDTO(
        Integer id,
        LocalDate notificationDate,
        LocalDate activityDate,
        String name,
        Double value,
        String description,
        String subjectName,
        Integer activityTypeId,
        String ActivityType
) {}
