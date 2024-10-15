/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.schedulers;

import com.example.constants.Environments;
import com.example.services.ext.Ship;
import com.example.services.ixt.Port;
import java.util.logging.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *
 * @author abdulsalam
 */
@Component
public class ShipScheduler {

    @Scheduled(fixedRate = 60000) // Polling every 60 seconds.
    public void pollShipLocations() {
        if (!Environments.IS_SCHEDULER_ACTIVE) return;
        
        Logger.getLogger(ShipScheduler.class.getName()).info("Scheduler running");        
        Ship.Runner().thenAccept(ships -> {
            Logger.getLogger(ShipScheduler.class.getName()).info(ships.toString());
            Port.Do(ships);
        });
    }
}
