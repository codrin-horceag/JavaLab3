package com.example.lab3.controller;

import com.example.lab3.model.Championship;
import com.example.lab3.model.Team;
import com.example.lab3.repository.TeamsDB;
import com.example.lab3.service.GenerateChampionship;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

@ManagedBean
@RequestScoped
public class TeamsController implements Serializable {
    private List<Team> teams;
    private TeamsDB teamsDB;
    private List<Championship> combinationList;
    private String selectedTeamName;
    private String selectedTeamDate;
    private Long selectedTeamCity;

    @PostConstruct
    public void init(){
        this.teamsDB = new TeamsDB();
        try {
            this.teams = this.teamsDB.retrieveTeam();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Championship> getCombinationList() {
        System.out.println(combinationList);
        return combinationList;
    }

    public void setCombinationList(List<Championship > combinationList) {
        this.combinationList = combinationList;
    }

    public String getSelectedTeamName() {
        return selectedTeamName;
    }

    public void setSelectedTeamName(String selectedTeamName) {
        this.selectedTeamName = selectedTeamName;
    }

    public String getSelectedTeamDate() {
        return selectedTeamDate;
    }

    public void setSelectedTeamDate(String selectedTeamDate) {
        this.selectedTeamDate = selectedTeamDate;
    }

    public Long getSelectedTeamCity() {
        return selectedTeamCity;
    }

    public void setSelectedTeamCity(Long selectedTeamCity) {
        this.selectedTeamCity = selectedTeamCity;
    }
    public void generateChampionShip() throws IOException {
        GenerateChampionship generateChampionship = new GenerateChampionship(teams);
        this.combinationList = generateChampionship.getCombinationList();
        System.out.println(this.combinationList);
        FacesContext.getCurrentInstance().getExternalContext().redirect("championship.xhtml");
    }

    public List<Team> getTeams() {
        return this.teams;
    }

    public void addTeam(String name, String foundingDate, String cityName) throws SQLException {
        this.teamsDB.storeTeam(name, foundingDate, cityName);
    }

}
