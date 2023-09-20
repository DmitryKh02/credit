package ru.neoflex.dossier.exception;

public class EmailMessageConverterException extends RuntimeException{
    private final String message;
    private final String emailMessageDTO;

    public  EmailMessageConverterException(String message, String emailMessageDTO){
        super();
        this.message = message;
        this.emailMessageDTO = emailMessageDTO;
    }

    public String getError(){
        return message.concat(emailMessageDTO);
    }
}
