package com.example.geektrust.database;
import com.example.geektrust.exception.InvalidRideException;
import com.example.geektrust.model.Ride;
import java.util.*;

public class RideManager {

     Map<String, Ride> rideMap=new HashMap<>();

     public boolean isRidePresent(String rideId)
     {
         return rideMap.containsKey(rideId);
     }

     public Ride getRide(String rideId)
     {
         if(!isRidePresent(rideId))
             return null;

         return rideMap.get(rideId);
     }

     public void addRide(Ride ride)
     {
         rideMap.put(ride.getRideId(),ride);
     }

}
