package com.CRUDautores.tecylab.controller;

import com.CRUDautores.tecylab.DTO.AuthorRequestDTO;
import com.CRUDautores.tecylab.DTO.AuthorResponseDTO;
import com.CRUDautores.tecylab.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;
    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAllUsers() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @PostMapping
    public ResponseEntity<AuthorResponseDTO> createUser(@Valid @RequestBody AuthorRequestDTO authorRequestDTO) {
        AuthorResponseDTO authorCreado = authorService.createAuthor(authorRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(authorCreado);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<AuthorResponseDTO> findByEmail(@PathVariable String email) {
        return ResponseEntity.ok(authorService.findByEmail(email));
    }

    @GetMapping("/nationality/{nationality}")
    public ResponseEntity<List<AuthorResponseDTO>> findByNationality(@PathVariable String nationality) {
        return ResponseEntity.ok(authorService.findByNationality(nationality));
    }


}
