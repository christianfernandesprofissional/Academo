package com.academo.controller.dtos;

import java.time.LocalDate;

public record ProfilePutDTO(
        String fullName,
        LocalDate birthDate,
        Character gender,
        String institution) {
}
