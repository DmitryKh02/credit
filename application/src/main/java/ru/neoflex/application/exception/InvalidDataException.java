package ru.neoflex.application.exception;

import lombok.Getter;

import java.util.List;

@Getter
public class InvalidDataException extends RuntimeException{
    private final List<ErrorMessage> errorMessage;

    public InvalidDataException(List<ErrorMessage> errorMessage) {
        super();
        this.errorMessage = errorMessage;
    }
}
