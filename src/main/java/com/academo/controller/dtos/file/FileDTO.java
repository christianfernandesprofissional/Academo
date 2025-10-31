package com.academo.controller.dtos.file;

import java.time.LocalDateTime;

public record FileDTO(
        String uuid,
        String fileName,
        String path,
        String fileType,
        Long size,
        Integer subjectId,
        LocalDateTime createdAt
) {}
