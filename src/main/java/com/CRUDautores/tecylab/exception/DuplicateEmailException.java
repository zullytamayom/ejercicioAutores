package com.CRUDautores.tecylab.exception;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String email) {
        super("Author with email " + email + " already exists");
    }
}
