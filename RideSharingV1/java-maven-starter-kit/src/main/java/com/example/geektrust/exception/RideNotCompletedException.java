package com.example.geektrust.exception;

public class RideNotCompletedException extends RuntimeException{

    public RideNotCompletedException()
    {
        super("RIDE_NOT_COMPLETED");
    }
}
