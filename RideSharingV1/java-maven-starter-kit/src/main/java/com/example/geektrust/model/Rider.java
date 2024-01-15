package com.example.geektrust.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Rider {
    private String riderId;
    private Coordinate coordinate;
}
