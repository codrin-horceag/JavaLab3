package com.example.lab3.controller;

import com.example.lab3.model.Team;
import com.example.lab3.repository.TeamsDB;
import com.example.lab3.service.TeamsService;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ViewScoped
public class TeamsController implements Serializable {
    private List<Team> teams;
    private TeamsDB teamsDB = new TeamsDB();

    @Inject
    private TeamsService service;

    @PostConstruct
    public void init(){
        List<Team> teams = new ArrayList<>();
        try {
            teams = this.teamsDB.retrieveTeam();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public void addTeam(String name, String foundingDate, String cityName) throws SQLException {
        this.teamsDB.storeTeam(name, foundingDate, cityName);
    }
}
