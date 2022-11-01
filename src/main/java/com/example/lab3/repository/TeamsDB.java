package com.example.lab3.repository;

import com.example.lab3.model.City;
import com.example.lab3.model.Team;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamsDB {

    private Connection connection;

    public TeamsDB() {
        getConnection();
    }

    //compulsory lab4
    public Connection getConnection() {
        javax.naming.InitialContext ctx = null;
        try {
            ctx = new javax.naming.InitialContext();
            javax.sql.DataSource ds = (javax.sql.DataSource)ctx.lookup("jdbc/lab3");
            return ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void storeTeam(String teamName, String foundingDate, String cityName) throws SQLException {

        String retrieveCity = "select * from cities c where c.name = ?;";
        PreparedStatement statement = connection.prepareStatement(retrieveCity);
        statement.setString(1,cityName);

        ResultSet resultSet = statement.executeQuery();
        if(!resultSet.isBeforeFirst()){

            String insertCity = "insert into cities( name ) values (?)";
            PreparedStatement preparedStatementCity = connection.prepareStatement(insertCity);
            preparedStatementCity.setString(1,cityName);
            preparedStatementCity.executeUpdate();
        }

        String insertTeam = "insert into teams t (name, founding_date, city_id) values (?,?," +
                "(select id from cities c where c.name = ?))";
        PreparedStatement preparedStatementTeams = connection.prepareStatement(insertTeam);

        preparedStatementTeams.setString(1, teamName);
        preparedStatementTeams.setString(2, foundingDate);
        preparedStatementTeams.setString(3, cityName);

        preparedStatementTeams.executeUpdate();
    }

    public void storeCity(String name) throws SQLException {
        String storeCity = "INSERT INTO cities (name) VALUES (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(storeCity);

        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();

    }

    public List<Team> retrieveTeam() throws SQLException {

        String retriveteam = "SELECT * FROM teams";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(retriveteam);

        List<Team> teams = new ArrayList<>();

        while (resultSet.next()) {

            Team team = new Team(resultSet.getString("name"),
                    resultSet.getString("founding_date"),
                    resultSet.getLong("city_id"));
            teams.add(team);

        }
        statement.close();

        return teams;
    }

    public List<City> retrieveCities() throws SQLException {
        String retrieveCities = "SELECT * FROM cities";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(retrieveCities);

        List<City> cities = new ArrayList<>();

        while (resultSet.next()) {
            City city = new City(resultSet.getLong("id"),
                    resultSet.getString("name"));

            cities.add(city);
        }

        statement.close();
        return cities;
    }
}
