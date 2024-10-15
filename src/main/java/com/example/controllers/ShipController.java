/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controllers;

import com.example.constants.Defaults;
import com.example.models.ShipManager;
import com.example.services.ext.Ship;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abdulsalam
 */
@RestController
@RequestMapping("/ship")
public class ShipController {

    @Autowired
    private Ship serviceShip;

    @GetMapping(Defaults.STRINGS)
    public List<ShipManager> list() {
        return this.serviceShip.Current();
    }
}
