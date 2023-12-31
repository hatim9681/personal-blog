package com.blogpersonnel.Commentaire.exception;

import com.blogpersonnel.Commentaire.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice

public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(CommentNotFoundForBlogIdException.class)
    public ResponseEntity<ErrorResponse> handleClientServiceException(CommentNotFoundForBlogIdException clientHandleException){
        return  new ResponseEntity<>( new ErrorResponse().builder()
                .errorCode(clientHandleException.getErroCode())
                .errorMessage(clientHandleException.getMessage())
                .build(), HttpStatus.BAD_REQUEST);

    }
}
