package com.enigma.kingkost.handlerExeptions;

import com.enigma.kingkost.dto.response.HandleExeptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice()
public class HandleResponse {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public HandleExeptionResponse handleNotFoundExeption(NotFoundException notFoundException) {
        return HandleExeptionResponse.builder()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .message(notFoundException.getMessage())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public HandleExeptionResponse handleExeptionResponseMissingParam(MissingServletRequestParameterException ex) {
        return HandleExeptionResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Required request parameter '" + ex.getParameterName())
                .build();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NullPointerException.class)
    public HandleExeptionResponse handleExeptionResponseNull(NullPointerException nullPointerException) {
        return HandleExeptionResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message(nullPointerException.getMessage())
                .build();
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public HandleExeptionResponse handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return HandleExeptionResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Validation error")
                .build();
    }

}
