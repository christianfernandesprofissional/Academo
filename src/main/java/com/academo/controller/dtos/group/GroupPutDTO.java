package com.academo.controller.dtos.group;

import com.academo.controller.dtos.subject.SubjectDTO;

import java.util.List;

public record GroupPutDTO(
        Integer id,
        String name,
        String description,
        Boolean isActive,
        List<Integer> subjectsId
) {
}
