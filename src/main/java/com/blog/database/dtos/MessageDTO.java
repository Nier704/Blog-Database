package com.blog.database.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record MessageDTO(
        @NotBlank
        @Size(min = 3, max = 50)
        String name,
        @NotBlank
        @Size(max = 500)
        String message
) {
}
