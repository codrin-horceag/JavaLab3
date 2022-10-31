package com.example.lab3.model;

public class Combination {
    private Long firstTeam;
    private Long secondTeam;

    public Combination(Long firstTeam, Long secondTeam) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public void setTeam(Long firstTeam, Long secondTeam){
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public Long getFirstTeam() {
        return firstTeam;
    }

    public Long getSecondTeam() {
        return secondTeam;
    }
}
