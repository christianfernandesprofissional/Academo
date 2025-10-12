package com.academo.controller.dtos.group;

import java.util.List;

public record AssociateSubjectsDTO(
        Integer groupId,
        List<Integer> subjectsIds
) {
}
