package br.com.agroaqua.api.shared.application.dto;

public record FieldErrorDetail(
        String field,
        String message
) { }