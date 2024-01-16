package com.example.geektrust.exception;

public class NoDriverAvailableException extends RuntimeException{

    public NoDriverAvailableException(){
        super("NO_DRIVERS_AVAILABLE");
    }
}
