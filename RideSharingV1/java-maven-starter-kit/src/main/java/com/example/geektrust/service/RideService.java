package com.example.geektrust.service;
import com.example.geektrust.exception.InvalidRideException;
import com.example.geektrust.printer.OutputPrinter;
import com.example.geektrust.strategy.BillingStrategy;
import com.example.geektrust.strategy.MatchStrategy;
import com.example.geektrust.database.RideManager;
import com.example.geektrust.database.RiderManager;
import com.example.geektrust.model.Coordinate;
import com.example.geektrust.model.Driver;
import com.example.geektrust.model.Ride;
import com.example.geektrust.model.Rider;
import java.util.*;

public class RideService {

    final RideManager rideManager;
    final MatchStrategy matchStrategy;
    final RiderManager riderManager;
    final OutputPrinter outputPrinter;

    public RideService(RideManager rideManager, MatchStrategy matchStrategy, RiderManager riderManager, OutputPrinter outputPrinter)
    {
        this.rideManager=rideManager;
        this.matchStrategy=matchStrategy;
        this.riderManager=riderManager;
        this.outputPrinter=outputPrinter;
    }

    public void startRide(String rideId,int n,String riderId)
    {
          try {
              if(rideManager.isRidePresent(rideId))
              {
                 throw new InvalidRideException();
              }

              List<Driver> matchedDriver = matchStrategy.matchDriver(riderId, false);

              Driver nthDriver = matchStrategy.getNthMatchedDriver(n, matchedDriver);

              nthDriver.setRiding(true);

              Rider rider = riderManager.getRider(riderId);

              Ride ride = Ride.builder().rideId(rideId)
                      .strCoordinate(rider.getCoordinate())
                      .rider(rider)
                      .driver(nthDriver).build();

              rideManager.addRide(ride);

              outputPrinter.printStartRide(ride);
          }
          catch (InvalidRideException invalidRideException)
          {
              outputPrinter.printInvalidRide();
          }
    }

    public void stopRide(String rideId,int x,int y,int timeTaken)
    {
        try {
            if(!rideManager.isRidePresent(rideId) || rideManager.getRide(rideId).isEnd())
            {
                throw new InvalidRideException();
            }

            Ride ride=rideManager.getRide(rideId);

            Coordinate destCoordinate=new Coordinate(x,y);

            //set driver status to available for ride.
            ride.getDriver().setRiding(false);

            //update driver coordinate
            ride.getDriver().setCoordinate(destCoordinate);

            ride.setDestCoordinate(destCoordinate);
            ride.setTimeTakenInMin(timeTaken);
            ride.setEnd(true);

            outputPrinter.printStopRide(ride);
        }
        catch (InvalidRideException invalidRideException)
        {
            outputPrinter.printInvalidRide();
        }
    }

}
