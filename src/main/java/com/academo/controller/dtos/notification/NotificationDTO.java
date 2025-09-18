package com.academo.controller.dtos.notification;

import com.academo.controller.dtos.activity.ActivityNotificationDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

public class NotificationDTO
{
    private String email;
    private List<ActivityNotificationDTO> activityNotificationDTOS;
    private static final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public NotificationDTO(String email, String activitiesJson) {
        this.email = email;
        this.activityNotificationDTOS = parseActivities(activitiesJson);
    }

    private static List<ActivityNotificationDTO> parseActivities(String activitiesJson) {
        try {
            return mapper.readValue(activitiesJson, new TypeReference<List<ActivityNotificationDTO>>() {});
        } catch (Exception e) {
            throw new RuntimeException("Erro ao parsear JSON", e);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ActivityNotificationDTO> getActivityNotificationDTOS() {
        return activityNotificationDTOS;
    }

    public void setActivityNotificationDTOS(List<ActivityNotificationDTO> activityNotificationDTOS) {
        this.activityNotificationDTOS = activityNotificationDTOS;
    }
}
