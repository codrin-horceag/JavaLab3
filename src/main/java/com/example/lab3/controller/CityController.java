package com.example.lab3.controller;

import com.example.lab3.model.City;
import com.example.lab3.model.Team;
import com.example.lab3.repository.TeamsDB;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
@ApplicationScoped
public class CityController implements Serializable {
    private List<City> cities;
    private TeamsDB teamsDB;

    @PostConstruct
    public void init() {
        this.teamsDB = new TeamsDB();
        try {
            this.cities = this.teamsDB.retrieveCities();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<City> getCities() { return this.cities; }

    public void addCity(String name){

    }
}
