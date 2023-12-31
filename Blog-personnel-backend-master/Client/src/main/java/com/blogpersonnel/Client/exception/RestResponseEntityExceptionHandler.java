package com.blogpersonnel.Client.exception;

import com.blogpersonnel.Client.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {
@ExceptionHandler(ClientHandleException.class)
    public ResponseEntity<ErrorResponse> handleClientServiceException(ClientHandleException clientHandleException){
    return  new ResponseEntity<>( new ErrorResponse().builder()
        .errorCode(clientHandleException.getErrorCode())
        .errorMessage(clientHandleException.getMessage())
        .build(), HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(DifferentPasswordException.class)
    public ResponseEntity<ErrorResponse>  handleDifferPAssword(DifferentPasswordException differentPasswordException){
        return  new ResponseEntity<>( new ErrorResponse().builder()
                .errorCode(differentPasswordException.getErrorCode())
                .errorMessage(differentPasswordException.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ErrorResponse> InvalidPass(InvalidPasswordException exception){
    return new ResponseEntity<>(new ErrorResponse().builder()
            .errorMessage(exception.getMessage())
            .errorCode(exception.getErroCode())
            .build(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ImageNoFound.class)
    public ResponseEntity<ErrorResponse> InvalidPass(ImageNoFound exception){
        return new ResponseEntity<>(new ErrorResponse().builder()
                .errorMessage(exception.getMessage())
                .errorCode(exception.getErrorCode())
                .build(),HttpStatus.BAD_REQUEST);
    }

}
