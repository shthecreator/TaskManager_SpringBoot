package org.example.springeducationproj.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CreateTaskRequest(
        @NotBlank(message = "Название задачи обязательно")
        String title,

        String description,

        @NotNull(message = "ID пользователя обязателен")
        @Positive(message = "ID пользователя должен быть положительным")
        Long userId,

        @NotNull(message = "ID категории обязателен")
        @Positive(message = "ID категории должен быть положительным")
        Long categoryId
) {}
