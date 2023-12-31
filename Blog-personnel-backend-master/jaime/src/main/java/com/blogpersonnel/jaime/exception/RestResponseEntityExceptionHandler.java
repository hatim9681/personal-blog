package com.blogpersonnel.jaime.exception;

import com.blogpersonnel.jaime.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(LikeNotFoundForBlogIdException.class)
    public ResponseEntity<ErrorResponse> handleClientServiceException(LikeNotFoundForBlogIdException clientHandleException){
        return  new ResponseEntity<>( new ErrorResponse().builder()
                .errorCode(clientHandleException.getErroCode())
                .errorMessage(clientHandleException.getMessage())
                .build(), HttpStatus.BAD_REQUEST);

    }
}
