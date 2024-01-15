package com.example.geektrust.strategy;

import com.example.geektrust.database.RideManager;
import com.example.geektrust.model.Coordinate;
import com.example.geektrust.model.Ride;
import java.text.DecimalFormat;

public class DefaultBillingStrategy implements BillingStrategy{

    private final double BASE_FARE=50;
    private final double KM_CHARGE=6.5;
    private final double MIN_CHARGE=2;
    private final double SERVICE_TAX=0.2;

    RideManager rideManager;

    public DefaultBillingStrategy(RideManager rideManager){
      this.rideManager=rideManager;
    }

    @Override
    public double getRideBill(String rideId) {

        if(!rideManager.isRidePresent(rideId))
        {
            System.out.println("INVALID_RIDE");
        }

        Ride ride=rideManager.getRide(rideId);

        if(!ride.isEnd())
        {
            System.out.println("RIDE_NOT_COMPLETED");
        }

        Coordinate src=ride.getStrCoordinate();
        Coordinate des=ride.getDestCoordinate();

        double distanceTravelled=src.getDistance(des);
        double timeTakenInMin=ride.getTimeTakenInMin();

        return calculateFare(distanceTravelled,timeTakenInMin);
    }

    private double calculateFare(double distanceTravelled,double timeTakenInMin)
    {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(java.math.RoundingMode.HALF_UP);

        double totalAmountWithoutTax=Double.valueOf(df.format(BASE_FARE+(distanceTravelled*KM_CHARGE)+(timeTakenInMin*MIN_CHARGE)));
        double serviceTax=Double.valueOf(df.format(totalAmountWithoutTax*SERVICE_TAX));

        return totalAmountWithoutTax+serviceTax;

    }
}
