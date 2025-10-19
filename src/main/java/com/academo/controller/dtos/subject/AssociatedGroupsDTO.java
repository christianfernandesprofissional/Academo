package com.academo.controller.dtos.subject;

import java.util.List;

public record AssociatedGroupsDTO(
        Integer id,
        String name,
        String description,
        Boolean isActive,
        List<String> group
) {
}
