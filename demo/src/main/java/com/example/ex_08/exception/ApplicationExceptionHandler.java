package com.example.ex_08.exception;

import com.example.ex_08.constant.Constant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorMessage> handleSystemError(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErrorMessage.builder().code(Constant.SYSTEM_ERROR).message(e.getMessage()).build());
    }

    @ExceptionHandler(value = InvalidInputException.class)
    public ResponseEntity<ErrorMessage> handleInvalidInputException(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessage.builder().code(Constant.INVALID_INPUT).message(e.getMessage()).build());
    }

}
