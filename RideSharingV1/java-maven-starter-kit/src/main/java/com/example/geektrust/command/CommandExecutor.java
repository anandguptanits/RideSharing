package com.example.geektrust.command;
import com.example.geektrust.database.*;
import com.example.geektrust.model.Coordinate;
import com.example.geektrust.model.Rider;
import com.example.geektrust.printer.OutputPrinter;
import com.example.geektrust.service.RideService;
import com.example.geektrust.strategy.*;

public class CommandExecutor {

    final DriverManager driverManager=new DriverManager();
    final RiderManager riderManager=new RiderManager();
    final RideManager rideManager=new RideManager();
    OutputPrinter outputPrinter=new OutputPrinter();
    final MatchStrategy matchStrategy=new MatchWithinLimitStrategy(driverManager,riderManager,outputPrinter);
    final BillingStrategy billingStrategy=new DefaultBillingStrategy(rideManager,outputPrinter);

    RideService rideService=new RideService(rideManager,matchStrategy,riderManager,outputPrinter);

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
            billingStrategy.generateBill(command[1]);
        }
    }

}
