package com.miniproject.amys.exception;

public class DataIntegrityException extends RuntimeException{
    public DataIntegrityException(String message){
        super(message);
    }
}
