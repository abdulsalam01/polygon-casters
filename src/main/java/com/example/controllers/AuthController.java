/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controllers;

import com.example.models.AuthManager;
import com.example.services.ext.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author abdulsalam
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private Auth serviceAuth;
        
    @PostMapping("/login")
    public AuthManager login() {
        return this.serviceAuth.Current();
    }
}
