package com.example.geektrust.strategy;
import com.example.geektrust.Constants;
import com.example.geektrust.database.DriverManager;
import com.example.geektrust.database.RiderManager;
import com.example.geektrust.exception.InvalidRideException;
import com.example.geektrust.exception.NoDriverAvailableException;
import com.example.geektrust.model.Driver;
import com.example.geektrust.model.Rider;
import com.example.geektrust.printer.OutputPrinter;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class MatchWithinLimitStrategy implements MatchStrategy{

    final DriverManager driverManager;
    final RiderManager riderManager;
    final OutputPrinter outputPrinter;

    public MatchWithinLimitStrategy(DriverManager driverManager, RiderManager riderManager,OutputPrinter outputPrinter)
    {
        this.driverManager=driverManager;
        this.riderManager=riderManager;
        this.outputPrinter=outputPrinter;
    }

    public List<Driver> matchDriver(String riderId,boolean print) {

        Rider rider = riderManager.getRider(riderId);

        List<Driver> matchedDriver= driverManager.getDrivers().stream().
                filter(driver -> !driver.isRiding() && driver.getDistanceFromRider(rider)<= Constants.MATCH_LIMIT_KM).
                sorted((d1,d2)->{
                    if(d1.getDistanceFromRider(rider)==d2.getDistanceFromRider(rider))
                        return d1.getDriverId().compareTo(d2.getDriverId());

                    return  Double.compare(d1.getDistanceFromRider(rider),d2.getDistanceFromRider(rider));
                }).collect(Collectors.toList());

        if(print)
        {
            outputPrinter.printMatchedDrivers(matchedDriver);
        }

        return matchedDriver;
    }

    public Driver getNthMatchedDriver(int n,List<Driver> matchedDriver)
    {
        if(matchedDriver.isEmpty() || matchedDriver.size()<n){
            throw new InvalidRideException();
        }
        return matchedDriver.get(n-1);
    }

}
