package com.example.geektrust.strategy;
import com.example.geektrust.Constants;
import com.example.geektrust.database.RideManager;
import com.example.geektrust.exception.InvalidRideException;
import com.example.geektrust.exception.RideNotCompletedException;
import com.example.geektrust.model.Ride;
import com.example.geektrust.printer.OutputPrinter;

import java.text.DecimalFormat;

public class DefaultBillingStrategy implements BillingStrategy{

    RideManager rideManager;
    OutputPrinter outputPrinter;

    public DefaultBillingStrategy(RideManager rideManager,OutputPrinter outputPrinter){
      this.rideManager=rideManager;
      this.outputPrinter=outputPrinter;
    }

    @Override
    public void generateBill(String rideId) {

        try{
            if(!rideManager.isRidePresent(rideId))
            {
                throw new InvalidRideException();
            }
            Ride ride=rideManager.getRide(rideId);

            if(!ride.isEnd())
            {
                throw new RideNotCompletedException();
            }

            double billAmount=calculateRideFare(ride);

            outputPrinter.printBill(ride,billAmount);
        }
        catch (InvalidRideException invalidRideException){
            outputPrinter.printInvalidRide();
        }catch (RideNotCompletedException rideNotCompletedException)
        {
            outputPrinter.printRideNotCompleted();
        }
    }

    private double calculateRideFare(Ride ride)
    {
        double distanceTravelled=ride.getStrCoordinate().getDistance(ride.getDestCoordinate());
        double timeTakenInMin=ride.getTimeTakenInMin();

        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(java.math.RoundingMode.HALF_UP);

        double totalAmountWithoutTax=Double.valueOf(df.format(Constants.BASE_FARE+(distanceTravelled*Constants.KM_CHARGE)+(timeTakenInMin*Constants.MIN_CHARGE)));
        double serviceTax=Double.valueOf(df.format(totalAmountWithoutTax*Constants.SERVICE_TAX));

        return totalAmountWithoutTax+serviceTax;

    }
}
