package com.example.geektrust.model;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Driver {

    private String driverId;
    private Coordinate coordinate;
    private boolean isRiding;

    public double getDistanceFromRider(Rider rider)
    {
        return coordinate.getDistance(rider.getCoordinate());
    }
}
