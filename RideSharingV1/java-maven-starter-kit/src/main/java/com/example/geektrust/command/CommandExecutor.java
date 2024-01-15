package com.example.geektrust.command;
import com.example.geektrust.database.*;
import com.example.geektrust.model.Coordinate;
import com.example.geektrust.model.Rider;
import com.example.geektrust.service.RideService;
import com.example.geektrust.strategy.*;

public class CommandExecutor {

    DriverManager driverManager=new DriverManager();
    RiderManager riderManager=new RiderManager();
    RideManager rideManager=new RideManager();
    MatchStrategy matchStrategy=new MatchWithinLimitStrategy(driverManager,riderManager);
    BillingStrategy billingStrategy=new DefaultBillingStrategy(rideManager);

    RideService rideService=new RideService(rideManager,matchStrategy,riderManager,billingStrategy);

    public void executeCommand(String[] command)
    {
        if (command[0].equals("ADD_DRIVER")) {
            driverManager.add(command[1], Integer.parseInt(command[2]), Integer.parseInt(command[3]));
        }else if(command[0].equals("ADD_RIDER"))
        {
            Rider rider=new Rider(command[1],new Coordinate(Integer.parseInt(command[2]),Integer.parseInt(command[3])));
            riderManager.addRider(rider);
        }else if(command[0].equals("MATCH"))
        {
            matchStrategy.matchDriver(command[1],true);
        }else if(command[0].equals("START_RIDE"))
        {
            rideService.startRide(command[1],Integer.parseInt(command[2]),command[3]);
        }else if(command[0].equals("STOP_RIDE"))
        {
            rideService.stopRide(command[1],Integer.parseInt(command[2]),Integer.parseInt(command[3]),Integer.parseInt(command[4]));
        }else if(command[0].equals("BILL"))
        {
            rideService.generateBill(command[1]);
        }
    }

}
