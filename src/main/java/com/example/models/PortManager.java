/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.models;

import java.util.Date;

/**
 *
 * @author abdulsalam
 */
public class PortManager {
    private String MMSI;
    private String event;
    private Date timeDate;

    public PortManager(String MMSI, String event, Date timeDate) {
        this.MMSI = MMSI;
        this.event = event;
        this.timeDate = timeDate;
    }    
    
    public String getMMSI() {
        return MMSI;
    }

    public void setMMSI(String MMSI) {
        this.MMSI = MMSI;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }    
    
    public Date getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(Date timeDate) {
        this.timeDate = timeDate;
    }    
}
