/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author abdulsalam
 */
public class ShipManager {
    @JsonProperty("_id")
    private String id;

    @JsonProperty("mssi")
    private String MMSI;
    
    @JsonProperty("location")
    private LocationManager location;

    @JsonProperty("courseOverGround")
    private float courseOverGround;
    
    @JsonProperty("timeLastUpdate")
    private long timeLastUpdate;
    
    @JsonProperty("imoNumber")
    private String imoNumber;
    
    @JsonProperty("name")
    private String name;

    @JsonProperty("shipType")
    private String shipType;
    
    @JsonProperty("status")    
    private String status;
    
    @JsonProperty("speedOverGround")
    private float speedOverGround;
    
    @JsonProperty("coms")
    private int coms;
    
    @JsonProperty("destination")
    private String destination;
    
    @JsonProperty("trueDestination")
    private String trueDestination;
    
    @JsonProperty("extras")
    private Extras extras;

    @JsonIgnore
    @JsonProperty("calculatedHeading")
    private String calculatedHeading;
    
    @JsonIgnore
    @JsonProperty("category")
    private String category;    
    
    @JsonIgnore
    @JsonProperty("eni")
    private String eni;
    
    @JsonIgnore
    @JsonProperty("callSign")
    private String callSign;

    public ShipManager() {
    }

    public ShipManager(String id, String MMSI, LocationManager location) {
        this.id = id;
        this.MMSI = MMSI;
        this.location = location;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMMSI() {
        return MMSI;
    }

    public void setMMSI(String MMSI) {
        this.MMSI = MMSI;
    }

    public LocationManager getLocation() {
        return location;
    }

    public void setLocation(LocationManager location) {
        this.location = location;
    }

    public float getCourseOverGround() {
        return courseOverGround;
    }

    public void setCourseOverGround(float courseOverGround) {
        this.courseOverGround = courseOverGround;
    }

    public long getTimeLastUpdate() {
        return timeLastUpdate;
    }

    public void setTimeLastUpdate(long timeLastUpdate) {
        this.timeLastUpdate = timeLastUpdate;
    }

    public String getImoNumber() {
        return imoNumber;
    }

    public void setImoNumber(String imoNumber) {
        this.imoNumber = imoNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShipType() {
        return shipType;
    }

    public void setShipType(String shipType) {
        this.shipType = shipType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getSpeedOverGround() {
        return speedOverGround;
    }

    public void setSpeedOverGround(float speedOverGround) {
        this.speedOverGround = speedOverGround;
    }

    public int getComs() {
        return coms;
    }

    public void setComs(int coms) {
        this.coms = coms;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTrueDestination() {
        return trueDestination;
    }

    public void setTrueDestination(String trueDestination) {
        this.trueDestination = trueDestination;
    }

    public Extras getExtras() {
        return extras;
    }

    public void setExtras(Extras extras) {
        this.extras = extras;
    }

    @Override
    public String toString() {
        return this.getId() + "_" + this.getMMSI() + "_" + this.getLocation();
    }
    
    /**
     * Sub classes
     * Extractor from ship API responses.
     */
    public class Extras {        
        private int coms;

        public int getComs() {
            return coms;
        }

        public void setComs(int coms) {
            this.coms = coms;
        }        
    }
}
