package com.vytrack.enums;

public enum Titles {
    DASHBOARD("Dashboard"),LOGIN("Login"),VEHICLES("Car - Entities - System - Car - Entities - System"),
    ODOMETER("Vehicle Odometer - Entities - System - Car - Entities - System");
    private final String value;

    Titles(String value){
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
