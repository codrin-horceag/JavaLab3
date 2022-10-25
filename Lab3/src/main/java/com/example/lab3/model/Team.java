package com.example.lab3.model;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Named
@SessionScoped
public class Team implements Serializable {
    private String name;
    private String foundingDate;
    private City city;

    public Team(String name, String foundingDate, City city) {
        this.name = name;
        this.foundingDate = foundingDate;
        this.city = city;
    }

    public Team(){};

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFoundingDate() {
        return foundingDate;
    }

    public void setFoundingDate(String foundingDate) {
        this.foundingDate = foundingDate;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(name, team.name) && Objects.equals(foundingDate, team.foundingDate) && Objects.equals(city, team.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, foundingDate, city);
    }
}
