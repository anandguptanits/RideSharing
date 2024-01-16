package com.example.geektrust.printer;
import com.example.geektrust.exception.NoDriverAvailableException;
import com.example.geektrust.model.Driver;
import com.example.geektrust.model.Ride;

import java.util.List;

public class OutputPrinter {

    public void printInvalidRide()
    {
        System.out.println("INVALID_RIDE");
    }

    public void printStartRide(Ride ride)
    {
        System.out.println("RIDE_STARTED " + ride.getRideId());
    }
    public void printStopRide(Ride ride)
    {
        System.out.println("RIDE_STOPPED "+ride.getRideId());
    }

    public void printBill(Ride ride,double billAmount)
    {
        System.out.println("BILL "+ride.getRideId()+" "+ride.getDriver().getDriverId()+" "+String.format("%.2f",billAmount));
    }

    public void printRideNotCompleted()
    {
        System.out.println("RIDE_NOT_COMPLETED");
    }

    public void printMatchedDrivers(List<Driver> matchedDriver)
    {
        try{
            if(matchedDriver.isEmpty())
            {
                throw new NoDriverAvailableException();
            }
            System.out.print("DRIVERS_MATCHED ");
            matchedDriver.forEach(driver -> System.out.print(driver.getDriverId() + " "));
            System.out.println();

        }catch (NoDriverAvailableException noDriverAvailableException)
        {
            System.out.println("NO_DRIVERS_AVAILABLE");
        }
    }
}
