package com.academo.controller.dtos.activity;

import java.time.LocalDate;

public record ActivityPutDTO(
        Integer id,
        LocalDate date,
        String name,
        String description,
        Integer subjectId,
        Integer ActivityTypeId
) {}
