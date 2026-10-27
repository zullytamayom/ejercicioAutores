package com.CRUDautores.tecylab.mapper;

import com.CRUDautores.tecylab.DTO.AuthorRequestDTO;
import com.CRUDautores.tecylab.DTO.AuthorResponseDTO;
import com.CRUDautores.tecylab.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    @Mapping(target = "id", ignore = true)
    Author toEntity(AuthorRequestDTO requestDTO);

    AuthorResponseDTO toResponseDTO(Author entity);
    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(AuthorRequestDTO dto, @MappingTarget Author author);

}
