package com.example.geektrust.service;
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

    RideManager rideManager;
    MatchStrategy matchStrategy;
    RiderManager riderManager;
    BillingStrategy billingStrategy;

    public RideService(RideManager rideManager, MatchStrategy matchStrategy, RiderManager riderManager, BillingStrategy billingStrategy)
    {
        this.rideManager=rideManager;
        this.matchStrategy=matchStrategy;
        this.riderManager=riderManager;
        this.billingStrategy=billingStrategy;
    }

    public void startRide(String rideId,int n,String riderId)
    {
          if(rideManager.isRidePresent(rideId))
          {
            System.out.println("INVALID_RIDE");
            return;
          }

          List<Driver> matchedDriver=matchStrategy.matchDriver(riderId,false);

          if(matchedDriver.size()<n)
          {
              System.out.println("INVALID_RIDE");
              return;
          }

          Driver nthDriver=matchedDriver.get(n-1);
          nthDriver.setRiding(true);

          Rider rider=riderManager.getRider(riderId);

          Ride ride=Ride.builder().rideId(rideId)
                  .strCoordinate(rider.getCoordinate())
                  .rider(rider)
                  .driver(nthDriver).build();

          rideManager.addRide(ride);

          System.out.println("RIDE_STARTED "+ride.getRideId());
    }

    public void stopRide(String rideId,int x,int y,int timeTaken)
    {
          if(!rideManager.isRidePresent(rideId) || rideManager.getRide(rideId).isEnd())
          {
              System.out.println("INVALID_RIDE");
              return;
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

          System.out.println("RIDE_STOPPED "+ride.getRideId());
    }

    public void generateBill(String rideId)
    {
        if(!rideManager.isRidePresent(rideId))
        {
           System.out.println("INVALID_RIDE");
           return;
        }
        Ride ride=rideManager.getRide(rideId);
        double billAmount=billingStrategy.getRideBill(rideId);
        System.out.println("BILL "+rideId+" "+ride.getDriver().getDriverId()+" "+String.format("%.2f",billAmount));
    }
}
