package com.academo.controller.dtos.notification;

import com.academo.controller.dtos.activity.ActivityNotificationDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public record NotificationDTO(

        String email,
        List<ActivityNotificationDTO> activityNotificationDTOS

){    private static final ObjectMapper mapper = new ObjectMapper();

    // Construtor alternativo usado pelo Spring Data JPA
    public NotificationDTO(String email, String activitiesJson) {
        this(email, parseActivities(activitiesJson));
    }

    private static List<ActivityNotificationDTO> parseActivities(String activitiesJson) {
        try {
            return mapper.readValue(activitiesJson, new TypeReference<List<ActivityNotificationDTO>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Erro ao parsear JSON", e);
        }
    }
}
