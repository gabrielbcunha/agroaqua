package br.com.agroaqua.api.shared.domain.exception;

public class DomainAuthorizationException extends RuntimeException {
    public DomainAuthorizationException(String message) {
        super(message);
    }
}
