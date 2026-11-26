package org.example.springeducationproj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UpdateTaskRequest(
        @NotBlank(message = "Название задачи обязательно")
        String title,

        String description,

        @NotNull(message = "ID категории обязателен")
        @Positive(message = "ID категории должен быть положительным")
        Long categoryId,

        boolean completed
){}
