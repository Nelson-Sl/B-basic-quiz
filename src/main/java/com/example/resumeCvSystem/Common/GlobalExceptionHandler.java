package com.example.resumeCvSystem.Common;

import com.example.resumeCvSystem.Exception.NewUserEducationInfoInvalidException;
import com.example.resumeCvSystem.Exception.NewUserInfoInvalidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> handler (MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorMessage errorMessage = ErrorMessage.builder()
                .timeStamp(GlobalVariables.dateFormat.format(new Date()))
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(message).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ExceptionHandler({NewUserInfoInvalidException.class, NewUserEducationInfoInvalidException.class})
    public ResponseEntity<ErrorMessage> userInfoExceptionHandler(Exception ex) {
        String message = ex.getMessage();
        ErrorMessage errorMessage = ErrorMessage.builder()
                .timeStamp(GlobalVariables.dateFormat.format(new Date()))
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(message).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorMessage> userIdExceptionHandler (MethodArgumentTypeMismatchException ex) {
        String message = ExceptionMessage.USER_INVALID_ID_EXCEPTION_MESSAGE;
        ErrorMessage errorMessage = ErrorMessage.builder()
                .timeStamp(GlobalVariables.dateFormat.format(new Date()))
                .status(HttpStatus.BAD_REQUEST.value())
                .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .message(message).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
