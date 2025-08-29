package com.academo.controller.dtos;

import com.academo.model.Subject;
import com.academo.model.User;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

public record GroupPutDTO (
        Integer id,
        String name,
        String description,
        Integer user
){}
