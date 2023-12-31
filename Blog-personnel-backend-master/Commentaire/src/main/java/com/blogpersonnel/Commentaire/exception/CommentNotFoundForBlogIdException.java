package com.blogpersonnel.Commentaire.exception;

import lombok.Data;

@Data
public class CommentNotFoundForBlogIdException extends  RuntimeException{

    private String erroCode;
    public CommentNotFoundForBlogIdException(String erroMessage , String erroCode){
        super(erroMessage);
        this.erroCode=erroCode;
    }
}
