package com.riffo.users.exception;

public class EmailDejaUtiliseException extends RuntimeException {
    public EmailDejaUtiliseException(String email) {
        super("Un partenaire avec l'email '" + email + "' existe déjà");
    }
}
