package com.academo.controller.dtos.group;

import com.academo.controller.dtos.subject.SubjectDTO;
import com.academo.model.Subject;

import java.util.List;

public record GroupDTO (
        Integer id,
        String name,
        String description,
        List<SubjectDTO> subjects
){}
