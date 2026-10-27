package com.CRUDautores.tecylab.repository;

import com.CRUDautores.tecylab.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long> {

    Optional<Author> findByEmailIgnoreCase(String email);
    Optional<Author>  findByNationality ( String nationality);
    boolean existsByEmailIgnoreCase(String email);
    boolean existsByEmailIgnoreCaseAndIdNot(String email, Long id);
}
