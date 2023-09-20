package ru.neoflex.dossier.exception;

import lombok.Getter;

@Getter
public class NoSuchThemeException extends RuntimeException{
    private final String message;
    private final String invalidField;

    public NoSuchThemeException(String message, String invalidField){
        super();
        this.message = message;
        this.invalidField = invalidField;
    }

    public String getError(){
        return message.concat(invalidField);
    }
}
