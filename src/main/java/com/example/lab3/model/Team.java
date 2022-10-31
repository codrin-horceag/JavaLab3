package com.example.lab3.model;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;


@Named
@SessionScoped
public class Team implements Serializable {
    private Long id;
    private String name;
    private String foundingDate;
    private Long cityId;

    public Team(String name, String foundingDate, Long cityId) {
        this.name = name;
        this.foundingDate = foundingDate;
        this.cityId = cityId;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) && Objects.equals(name, team.name) && Objects.equals(foundingDate, team.foundingDate) && Objects.equals(cityId, team.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, foundingDate, cityId);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", foundingDate='" + foundingDate + '\'' +
                ", cityId=" + cityId +
                '}';
    }
}
