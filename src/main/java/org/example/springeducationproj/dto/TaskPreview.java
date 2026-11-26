package org.example.springeducationproj.dto;

public record TaskPreview(
        Long id,
        String title,
        String category,
        boolean completed
) {}
