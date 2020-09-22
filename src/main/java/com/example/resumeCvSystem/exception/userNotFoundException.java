package com.example.resumeCvSystem.exception;

public class userNotFoundException extends RuntimeException{
    public userNotFoundException(String message) {
        super(message);
    }
}
