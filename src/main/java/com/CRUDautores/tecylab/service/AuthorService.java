package com.CRUDautores.tecylab.service;

import com.CRUDautores.tecylab.DTO.AuthorRequestDTO;
import com.CRUDautores.tecylab.DTO.AuthorResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AuthorService {

    AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO);
    AuthorResponseDTO updateAuthor(Long id,AuthorRequestDTO authorRequestDTO);

   List<AuthorResponseDTO> findAll();
   AuthorResponseDTO findById(Long id);
    AuthorResponseDTO findByEmail(String email);

    List<AuthorResponseDTO> findByNationality(String nationality);

    void deleteAuthor(Long id);
}
