package com.example.geektrust.exception;

public class InvalidRideException extends RuntimeException{

    public InvalidRideException()
    {
        super("INVALID_RIDE");
    }
}
