/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.helpers;

import com.example.constants.Ports;
import com.example.models.LocationManager;
import com.example.models.ShipManager;

/**
 *
 * @author abdulsalam
 */
public class Polygon {

    // Method to determine if the ship's coordinates are inside the polygon.
    // Reference: https://medium.com/@girishajmera/exploring-algorithms-to-determine-points-inside-or-outside-a-polygon-038952946f87
    private static boolean isInside(double[][] polygon, LocationManager location) {
        double x = location.getCoordinates()[0];
        double y = location.getCoordinates()[1];

        int n = polygon.length;
        boolean inside = false;

        // Iterate over each edge of the polygon.
        for (int i = 0, j = n - 1; i < n; j = i++) {
            double xi = polygon[i][0], yi = polygon[i][1];
            double xj = polygon[j][0], yj = polygon[j][1];

            // Check if the y-coordinate of the point is between the y-coordinates of the polygon edge.
            boolean intersect = ((yi > y) != (yj > y))
                    && (x < (xj - xi) * (y - yi) / (yj - yi) + xi);

            // Toggle the inside status every time an intersection occurs.
            if (intersect) {
                inside = !inside;
            }
        }

        return inside;
    }

    // Exposed implementation of the function.
    public static boolean isInPort(ShipManager ship) {
        return Polygon.isInside(Ports.LISTS, ship.getLocation());
    }
}
