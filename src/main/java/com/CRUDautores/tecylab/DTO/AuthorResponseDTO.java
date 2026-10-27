package com.CRUDautores.tecylab.DTO;

public record AuthorResponseDTO(
        Long id,
        String firstName,
        String lastName,
        String email,
        String nationality,
        String status

) {
}
