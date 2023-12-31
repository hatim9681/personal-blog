package com.blogpersonnel.Client.exception;

import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Data
public class InvalidPasswordException extends RuntimeException{

    private String erroCode;
    public InvalidPasswordException(String message , String erroCode ){
        super(message);
        this.erroCode= erroCode;
    }
}
