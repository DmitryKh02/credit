package ru.neoflex.application.exception;

public record ErrorMessage(
        String fieldName,
        String message) {
}
