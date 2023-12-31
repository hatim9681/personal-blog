package com.blogpersonnel.jaime.exception;

import lombok.Data;

@Data
public class LikeNotFoundForBlogIdException extends  RuntimeException{

    private String erroCode;
    public LikeNotFoundForBlogIdException(String erroMessage , String erroCode){
        super(erroMessage);
        this.erroCode=erroCode;
    }
}
