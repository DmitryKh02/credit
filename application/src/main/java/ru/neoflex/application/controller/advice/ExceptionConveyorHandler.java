package ru.neoflex.application.controller.advice;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.neoflex.application.exception.BadRequestToServer;
import ru.neoflex.application.exception.InvalidDataException;
import ru.neoflex.application.exception.ErrorMessage;

import java.io.IOException;
import java.util.List;

@ControllerAdvice
public class ExceptionConveyorHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestToServer.class)
    public JsonNode onOtherServerException(BadRequestToServer ex) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(ex.getResponse());
    }
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDataException.class)
    public List<ErrorMessage> onInvalidDataException(InvalidDataException e) {
        return e.getErrorMessage();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public ErrorMessage onInvalidFormatException(InvalidFormatException e) {
        return new ErrorMessage(e.getTargetType().toString(), e.getMessage());
    }
}
