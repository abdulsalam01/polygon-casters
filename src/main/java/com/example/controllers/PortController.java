/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controllers;

import com.example.models.PortManager;
import com.example.services.ixt.Port;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abdulsalam
 */
@RestController
@RequestMapping("/port")
public class PortController {

    @Autowired
    private Port portService;
    
    @GetMapping("/event")
    public List<PortManager> getPortEvents() {
        return this.portService.getEvents();
    }
    
    @GetMapping("/ship")
    public Set<String> getCurrentShips() {
        return this.portService.getCurrentShipsInPort();
    }    
}
