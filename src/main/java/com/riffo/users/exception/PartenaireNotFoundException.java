package com.riffo.users.exception;

public class PartenaireNotFoundException extends RuntimeException {
    public PartenaireNotFoundException(Long id) {
        super("Partenaire introuvable avec l'ID : " + id);
    }

    public PartenaireNotFoundException(String message) {
        super(message);
    }
}
