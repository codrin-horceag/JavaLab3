package com.example.lab3.service;

import com.example.lab3.model.Championship;
import com.example.lab3.model.Combination;
import com.example.lab3.model.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ChampionshipService {
    private boolean exit = false;
    private final List<Championship> combinationList;

    public List<Championship> getCombinationList() {
        return combinationList;
    }

    public ChampionshipService(List<Team> teams){
        List<Combination> comb = new ArrayList<>();
        for(int i = 0; i < teams.size() - 1; i ++)
            for(int j = i + 1; j < teams.size(); j++) {
                comb.add(new Combination(new Long(i), new Long(j)));
            }
        Combination[][] matrix = new Combination[teams.size() - 1][teams.size() / 2];
        getChampionship(teams.size() - 1, matrix,0,teams.size()/2, comb);
        combinationList = new ArrayList<>();
        for(int i = 0; i < teams.size() - 1; i++)
        {
            for(int j = 0; j < teams.size() / 2; j++) {
                combinationList.add(new Championship(i, teams.get(matrix[i][j].getFirstTeam().intValue()).getName(),teams.get(matrix[i][j].getSecondTeam().intValue()).getName()));
            }
        }
    }

    public void getChampionship(int days, Combination[][] matrix, int game, int gamesPerDay, List<Combination> list){
        if(game == gamesPerDay){
            game = 0;
            days = days - 1;
        }
        if(days == 0){
            this.exit = true;
        }
        else{
            int found;
            List<Combination> tryList = new ArrayList<>(list);
            for(Combination combination : list) {
                found = 0;
                for (int i = 0; i < game; i++)
                    if(matrix[days - 1][i] != null)
                        if (Objects.equals(combination.getFirstTeam(), matrix[days - 1][i].getFirstTeam()) || Objects.equals(combination.getSecondTeam(), matrix[days - 1][i].getSecondTeam()) ||
                                Objects.equals(combination.getSecondTeam(), matrix[days - 1][i].getFirstTeam()) || Objects.equals(combination.getFirstTeam(), matrix[days - 1][i].getSecondTeam())) {
                            found = 1;
                            break;
                        }
                if (found == 0){
                    matrix[days - 1][game] = combination;
                    tryList.remove(combination);
                    getChampionship(days, matrix, game + 1, gamesPerDay,tryList);
                    if(this.exit)
                        return;
                    tryList.add(combination);
                    matrix[days - 1][game] = null;
                }
            }
        }
    }
}
