package com.example.lab3;

import com.example.lab3.controller.TeamsController;
import com.example.lab3.model.Team;
import com.example.lab3.repository.TeamsDB;

import java.io.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class TeamsServlet extends HttpServlet {
    private String message;

    private List<Team> teams = new ArrayList<>();

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        TeamsController controller = new TeamsController();
        controller.init();
        this.teams = controller.getTeams();
        request.setAttribute("teams", teams);
        request.getRequestDispatcher("./index.xhtml").forward(request, response);
    }

    public void destroy() {
    }
}
