package com.CRUDautores.tecylab.controller;

import com.CRUDautores.tecylab.DTO.AuthorRequestDTO;
import com.CRUDautores.tecylab.DTO.AuthorResponseDTO;
import com.CRUDautores.tecylab.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
@Validated
@Tag(name = "Authors", description = "Author management endpoints")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    @Operation(summary = "List active authors")
    @ApiResponse(responseCode = "200", description = "Active authors returned")
    public ResponseEntity<List<AuthorResponseDTO>> getAuthors() {
        return ResponseEntity.ok(authorService.findAll());
    }

    @PostMapping
    @Operation(summary = "Create an author")
    @ApiResponse(responseCode = "201", description = "Author created")
    @ApiResponse(responseCode = "400", description = "Request validation failed")
    @ApiResponse(responseCode = "409", description = "Email is already in use")
    public ResponseEntity<AuthorResponseDTO> createAuthor(
            @Valid @RequestBody AuthorRequestDTO authorRequest
    ) {
        AuthorResponseDTO createdAuthor = authorService.createAuthor(authorRequest);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAuthor.id())
                .toUri();
        return ResponseEntity.created(location).body(createdAuthor);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find an author by ID")
    @ApiResponse(responseCode = "200", description = "Author found")
    @ApiResponse(responseCode = "404", description = "Author not found")
    public ResponseEntity<AuthorResponseDTO> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.findById(id));
    }

    @GetMapping("/search")
    @Operation(summary = "Find an author by email")
    @ApiResponse(responseCode = "200", description = "Author found")
    @ApiResponse(responseCode = "400", description = "Email format is invalid")
    @ApiResponse(responseCode = "404", description = "Author not found")
    public ResponseEntity<AuthorResponseDTO> getAuthorByEmail(
            @RequestParam @Email String email
    ) {
        return ResponseEntity.ok(authorService.findByEmail(email));
    }

    @GetMapping("/nationality/{nationality}")
    @Operation(summary = "Find active authors by nationality")
    @ApiResponse(responseCode = "200", description = "Authors returned")
    public ResponseEntity<List<AuthorResponseDTO>> getAuthorsByNationality(
            @PathVariable String nationality
    ) {
        return ResponseEntity.ok(authorService.findByNationality(nationality));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an author")
    @ApiResponse(responseCode = "200", description = "Author updated")
    @ApiResponse(responseCode = "400", description = "Request validation failed")
    @ApiResponse(responseCode = "404", description = "Author not found")
    @ApiResponse(responseCode = "409", description = "Email is already in use")
    public ResponseEntity<AuthorResponseDTO> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody AuthorRequestDTO authorRequest
    ) {
        return ResponseEntity.ok(authorService.updateAuthor(id, authorRequest));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deactivate an author")
    @ApiResponse(responseCode = "204", description = "Author deactivated")
    @ApiResponse(responseCode = "404", description = "Author not found")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
