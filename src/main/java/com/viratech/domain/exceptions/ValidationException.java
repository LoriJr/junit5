package com.viratech.domain.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String mensagem){
        super(mensagem);
    }
}
