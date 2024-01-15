package com.example.geektrust.database;

import com.example.geektrust.model.Coordinate;
import com.example.geektrust.model.Driver;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DriverManager {

    Map<String, Driver> driverHashMap=new HashMap<>();

    public List<Driver> getDrivers()
    {
        return driverHashMap.values().stream().collect(Collectors.toList());
    }

    public void add(String driverId,int xCoordinate,int yCoordinate)
    {
        if(driverHashMap.containsKey(driverId))
        {
            System.out.println("Driver already present");
        }else{
            driverHashMap.put(driverId,new Driver(driverId,new Coordinate(xCoordinate,yCoordinate),false));
        }
    }
}
