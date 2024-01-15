package com.example.geektrust.model;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.DecimalFormat;

@AllArgsConstructor
@Data
public class Coordinate {
    private int x;
    private int y;


    public double getDistance(Coordinate to)
    {
        int diffX=this.x-to.getX();
        int diffY=this.y-to.getY();

        double sqrDistance=(double)(diffX*diffX)+(double) (diffY*diffY);
        double distance=Math.pow(sqrDistance,0.5);
        DecimalFormat df = new DecimalFormat("0.00");

        return Double.valueOf(df.format(distance));
    }

}
