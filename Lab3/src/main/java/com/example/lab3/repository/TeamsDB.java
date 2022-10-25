package com.example.lab3.repository;

import com.example.lab3.model.City;
import com.example.lab3.model.Team;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamsDB {

    private Connection connection;


    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/lab3", "postgres", "1234");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }

    public void storeTeam(String teamName, String foundingDate, String cityName) throws SQLException {

        String retrieveCity = "select * from cities c where c.cityname = ?;";
        PreparedStatement statement = connection.prepareStatement(retrieveCity);
        statement.setString(1,cityName);

        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.isBeforeFirst()){

            String insertCity = "insert into cities( cityname ) values (?)";
            PreparedStatement preparedStatementCity = connection.prepareStatement(insertCity);
            preparedStatementCity.setString(1,cityName);
            preparedStatementCity.executeUpdate();
        }

        String insertTeam = "insert into teams (teamname, foundingdate, cityid) values (?,?," +
                "(select id from cities where cityname = ?))";
        PreparedStatement preparedStatementTeams = connection.prepareStatement(insertTeam);

        preparedStatementTeams.setString(1, teamName);
        preparedStatementTeams.setString(2, foundingDate);
        preparedStatementTeams.setString(3, cityName);

        preparedStatementTeams.executeUpdate();
    }
    public List<Team> retrieveTeam() throws SQLException {

        String retriveteam = "SELECT t.teamname, t.foundingdate, c.cityname from teams t " +
                "join cities c on t.cityid = c.id;";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(retriveteam);

        List<Team> teams = new ArrayList<>();

        while (resultSet.next()) {

            Team team = new Team(resultSet.getString("teamname"),
                    resultSet.getString("foundingdate"),
                    new City(resultSet.getString("cityname")));
            teams.add(team);

        }
        statement.close();

        return teams;
    }
}
