package org.example.springeducationproj.dto;

public record TaskStatus(
        String application,
        String status,
        Long totalTasks
)
{}
