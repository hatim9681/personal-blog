package com.blogpersonnel.Client.exception;

import lombok.Data;

@Data
public class DifferentPasswordException extends RuntimeException{
    private String errorCode;
    public DifferentPasswordException(String message,String errorCode ){
        super(message);
        this.errorCode= errorCode;

    }
}
