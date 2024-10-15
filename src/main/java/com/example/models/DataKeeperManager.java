/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abdulsalam
 */
public class DataKeeperManager {

    private static List<ShipManager> shipsData = new ArrayList<>();

    // Save the ship data.
    public static void saveShips(List<ShipManager> ships) {
        DataKeeperManager.shipsData.clear();
        DataKeeperManager.shipsData.addAll(ships);
    }

    // Get the saved ship data.
    public static List<ShipManager> getShips() {
        return DataKeeperManager.shipsData;
    }

    // Clear the data (for manual clearing).
    public static void clearShips() {
        DataKeeperManager.shipsData.clear();
    }

}
