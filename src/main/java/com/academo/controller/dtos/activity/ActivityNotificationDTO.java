package com.academo.controller.dtos.activity;
import java.time.LocalDate;

public record ActivityNotificationDTO(
        String name,
        String description,
        String subject,
        LocalDate activityDate,
        LocalDate notificationDate
) {}