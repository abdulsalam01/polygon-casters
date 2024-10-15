/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.services.ixt;

import com.example.constants.Defaults;
import com.example.helpers.Polygon;
import com.example.models.PortManager;
import com.example.models.ShipManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author abdulsalam
 */
@Service
public class Port {

    private static Set<String> currentShipsInPort;
    private static List<PortManager> events;

    public Port() {
        Port.currentShipsInPort = new HashSet<>();
        Port.events = new ArrayList<>();
    }

    public static void Do(List<ShipManager> ships) {
        Set<String> newShipsInPort = new HashSet<>();

        for (ShipManager ship : ships) {
            if (Polygon.isInPort(ship)) {
                newShipsInPort.add(ship.getMMSI());

                // Detect entering the port.
                if (!Port.currentShipsInPort.contains(ship.getMMSI())) {
                    Port.addEvent(ship.getMMSI(), Defaults.ENTERS);
                }
            }
        }

        // Detect ships leaving the port.
        for (String mmsi : Port.currentShipsInPort) {
            if (!newShipsInPort.contains(mmsi)) {
                Port.addEvent(mmsi, Defaults.LEAVES);
            }
        }
        
        // Update the current ships in port.
        Port.currentShipsInPort = newShipsInPort;        
    }
    
    public Set<String> getCurrentShipsInPort() {
        return Port.currentShipsInPort;
    }

    public List<PortManager> getEvents() {
        return Port.events;
    }
    
    private static void addEvent(String mmsi, String event) {
        Port.events.add(new PortManager(mmsi, event, new Date()));
    }    
}
