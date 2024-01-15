package com.example.geektrust.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class Ride {

    private String rideId;
    private Coordinate strCoordinate;
    private Coordinate destCoordinate;
    private Driver driver;
    private Rider rider;
    private int timeTakenInMin;
    private boolean isEnd;


}
