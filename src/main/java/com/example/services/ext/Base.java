/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.services.ext;

import java.net.http.HttpClient;

/**
 *
 * @author abdulsalam
 */
public abstract class Base {

    protected static HttpClient httpClient;

    public Base() {
        Base.httpClient = HttpClient.newBuilder().build();
    }
}
