package com.example.geektrust.strategy;
import com.example.geektrust.database.DriverManager;
import com.example.geektrust.database.RiderManager;
import com.example.geektrust.model.Driver;
import com.example.geektrust.model.Rider;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MatchWithinLimitStrategy implements MatchStrategy{

    private final double MATCH_LIMIT_KM=5;
    DriverManager driverManager;
    RiderManager riderManager;

    public MatchWithinLimitStrategy(DriverManager driverManager, RiderManager riderManager)
    {
        this.driverManager=driverManager;
        this.riderManager=riderManager;
    }

    public List<Driver> matchDriver(String riderId,boolean print) {
        Rider rider = riderManager.getRider(riderId);

        List<Driver> matchedDriver= driverManager.getDrivers().stream().
                filter(driver -> !driver.isRiding() && driver.getDistanceFromRider(rider)<=MATCH_LIMIT_KM).
                sorted((d1,d2)->{
                    if(d1.getDistanceFromRider(rider)==d2.getDistanceFromRider(rider))
                        return d1.getDriverId().compareTo(d2.getDriverId());

                    return  Double.compare(d1.getDistanceFromRider(rider),d2.getDistanceFromRider(rider));
                }).collect(Collectors.toList());

        if(print)
        {
            printMatchedDriver(matchedDriver);
        }

        return matchedDriver;
    }

    private void printMatchedDriver(List<Driver> matchedDriver)
    {
        if(matchedDriver.isEmpty())
        {
            System.out.println("NO_DRIVERS_AVAILABLE");
            return;
        }

        System.out.print("DRIVERS_MATCHED ");

        for(Driver driver:matchedDriver)
        {
            System.out.print(driver.getDriverId()+" ");
        }
        System.out.println();
    }
}
