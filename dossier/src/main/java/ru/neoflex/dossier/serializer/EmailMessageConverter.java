package ru.neoflex.dossier.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.neoflex.dossier.dto.EmailMessage;
import ru.neoflex.dossier.exception.EmailMessageConverterException;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailMessageConverter {
    private final ObjectMapper objectMapper;

    public EmailMessage convert(String message) throws EmailMessageConverterException {
        try {
            return objectMapper.readValue(message, EmailMessage.class);
        } catch (JsonProcessingException ex) {
            log.error("Convert to EmailMessage error {}", ex.getMessage());
            throw new EmailMessageConverterException("Cannot convert string to DTO EmailMessage ", message);
        }
    }
}
