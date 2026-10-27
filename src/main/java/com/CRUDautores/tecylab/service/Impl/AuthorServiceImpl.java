package com.CRUDautores.tecylab.service.Impl;

import com.CRUDautores.tecylab.DTO.AuthorRequestDTO;
import com.CRUDautores.tecylab.DTO.AuthorResponseDTO;
import com.CRUDautores.tecylab.entity.Author;
import com.CRUDautores.tecylab.exception.DuplicateEmailException;
import com.CRUDautores.tecylab.mapper.AuthorMapper;
import com.CRUDautores.tecylab.repository.AuthorRepository;
import com.CRUDautores.tecylab.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;


    @Override
    public AuthorResponseDTO createAuthor(AuthorRequestDTO authorRequestDTO) {
        String email = normalizeEmail(authorRequestDTO.email());
        if (authorRepository.existsByEmailIgnoreCase(email)) {
            throw new DuplicateEmailException(email);
        }

        Author nuevoAuthor = authorMapper.toEntity(authorRequestDTO);
        nuevoAuthor.setEmail(email);
        return authorMapper.toResponseDTO(authorRepository.save(nuevoAuthor));
    }

    @Override
    public AuthorResponseDTO updateAuthor(Long id, AuthorRequestDTO authorRequestDTO) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author with id " + id + " not found"));

        String email = normalizeEmail(authorRequestDTO.email());
        if (authorRepository.existsByEmailIgnoreCaseAndIdNot(email, id)) {
            throw new DuplicateEmailException(email);
        }
        authorMapper.updateEntityFromDTO(authorRequestDTO, author);
        author.setEmail(email);

        return authorMapper.toResponseDTO(authorRepository.save(author));
    }

    @Override
    public List<AuthorResponseDTO> findAll() {
        return authorRepository.findAll()
                .stream()
                .map(authorMapper::toResponseDTO)
                .toList();
    }

    @Override
    public AuthorResponseDTO findById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Author with id " + id + " not found"));
        return authorMapper.toResponseDTO(author);
    }

    @Override
    public AuthorResponseDTO findByEmail(String email) {
        Author author = authorRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new EntityNotFoundException("Author with email " + email + " not found"));
        return authorMapper.toResponseDTO(author);

    }

    @Override
    public List<AuthorResponseDTO> findByNationality(String nationality) {
        return authorRepository.findByNationality(nationality)
                .stream()
                .map(authorMapper::toResponseDTO)
                .toList();
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new EntityNotFoundException("Author with id " + id + " not found");
        }
        authorRepository.deleteById(id);
    }

    private String normalizeEmail(String email) {
        return email.toLowerCase(Locale.ROOT);
    }
}
