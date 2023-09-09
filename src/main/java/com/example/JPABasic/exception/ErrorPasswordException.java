package com.example.JPABasic.exception;

public class ErrorPasswordException extends RuntimeException{
    public ErrorPasswordException(String message){
        super(message);
    }
}
