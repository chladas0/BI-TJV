package cz.cvut.fit.tjv.issuetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EntityStateException.class)
    public ResponseStatusException handleEntityStateException(EntityStateException e) {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler
    public ResponseStatusException handleIllegalDataException(IllegalDataException e) {
        return new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
    }
}