package com.example.geektrust.strategy;

import com.example.geektrust.model.Driver;

import java.util.List;

public interface MatchStrategy {

    List<Driver> matchDriver(String riderId,boolean print);
}
