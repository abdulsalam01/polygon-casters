/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.helpers;

import com.example.models.LocationManager;
import com.example.models.ShipManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author abdulsalam
 */
public class Seeder {

    public static List<ShipManager> embedShipData(int num) {
        Random rand = new Random();
        List<ShipManager> shipList = new ArrayList<>();
        
        for (int i = 1; i <= num; i++) {
            double n = rand.nextInt(num) / (i + 1);
            double x = 2 + n;
            double y = 50 + n;
            
            LocationManager l = new LocationManager("Point", new double[]{x, y});
            ShipManager s = new ShipManager("id_" + i, "mmsi_" + i, l);
            
            shipList.add(s);
        }
        
        return shipList;
    }
}
