package gb.spring.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.StringJoiner;

@RestControllerAdvice
public class ValidationHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handler(MethodArgumentNotValidException e) {
        StringJoiner sj = new StringJoiner("\n");
        e.getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).forEach(sj::add);
        return sj.toString();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handler(ConstraintViolationException e) {
        StringJoiner sj = new StringJoiner("\n");
        e.getConstraintViolations().forEach(error -> {
            String path = error.getPropertyPath().toString();
            sj.add(path.substring(path.lastIndexOf('.') + 1) + ": " + error.getMessage());
        });
        return sj.toString();
    }
}
