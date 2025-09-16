package com.academo.controller.dtos.activity;

import java.time.LocalDate;

public record ActivityPutDTO(
        Integer id,
        LocalDate activityDate,
        LocalDate notificationDate,
        String name,
        String description,
        Integer subjectId,
        Integer ActivityTypeId
) {}
