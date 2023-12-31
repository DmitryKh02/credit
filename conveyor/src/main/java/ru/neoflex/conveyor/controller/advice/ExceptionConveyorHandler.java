package ru.neoflex.conveyor.controller.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.neoflex.conveyor.exception.InvalidDataException;
import ru.neoflex.conveyor.exception.InvalidField;

import java.util.List;

@ControllerAdvice
public class ExceptionConveyorHandler {
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidDataException.class)
    public List<InvalidField> onInvalidDataException(InvalidDataException e) {
        return e.getInvalidField();
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public InvalidField onInvalidFormatException(InvalidFormatException e) {
        return new InvalidField(e.getTargetType().toString(), e.getMessage());
    }
}
