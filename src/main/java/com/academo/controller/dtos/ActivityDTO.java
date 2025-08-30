package com.academo.controller.dtos;

import java.time.LocalDate;

public record ActivityDTO(
        Integer id,
        LocalDate date,
        String name,
        String description,
        Integer subjectId,
        Integer typeActivityId
) {}
