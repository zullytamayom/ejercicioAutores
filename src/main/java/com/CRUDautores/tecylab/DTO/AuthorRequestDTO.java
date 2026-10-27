package com.CRUDautores.tecylab.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthorRequestDTO(

        @NotBlank(message = "El nombre es obligatorio")
        String firstName,
        @NotBlank(message = "El apellido es obligatorio")
        String lastName,
        @NotBlank(message = "El email es obligatorio")
        @Email
        String email,
        @NotBlank(message = "La nacionalidad es obligatorio")
        String nationality
) {
}
