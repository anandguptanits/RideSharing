package com.example.geektrust.database;
import com.example.geektrust.model.Rider;
import java.util.*;

public class RiderManager {

    Map<String, Rider> riderMap=new HashMap<>();

    public void addRider(Rider rider)
    {
        if(riderMap.containsKey(rider.getRiderId()))
        {
            System.out.println("Driver already present");
            return;
        }
        riderMap.put(rider.getRiderId(),rider);
    }

    public Rider getRider(String riderId)
    {
        if(!riderMap.containsKey(riderId))
        {
            System.out.println("Driver not present");
            return null;
        }
        return riderMap.get(riderId);
    }
}
