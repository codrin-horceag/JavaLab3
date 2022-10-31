package com.example.lab3.model;

public class Championship {
    private int day;
    private String home;
    private String away;

    public Championship(int day, String home, String away) {
        this.day = day;
        this.home = home;
        this.away = away;
    }

    public int getDay() {
        return day;
    }

    public String getHome() {
        return home;
    }

    public String getAway() {
        return away;
    }
}
