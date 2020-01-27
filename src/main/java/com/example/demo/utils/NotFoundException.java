package com.example.demo.utils;

public class NotFoundException extends RuntimeException {
    private int status;

    public NotFoundException(int status, String message) {
        super(message);
        this.status = status;
    }

    public NotFoundException(String s) {
        super(s);
    }
}
