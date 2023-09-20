package ru.neoflex.gateway.exception;

public record ErrorMessage(
        String fieldName,
        String message) {
}
