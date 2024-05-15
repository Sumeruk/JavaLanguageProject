package org.example.servlets;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;

public class Servlets extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setContentType("application/json");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException{
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();

        String name = req.getParameter("username");
        String age = req.getParameter("userage");
        String gender = req.getParameter("gender");
        String country = req.getParameter("country");
        String[] courses = req.getParameterValues("courses");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", name);
        jsonObject.put("userage", age);
        jsonObject.put("gender", gender);
        jsonObject.put("country", country);
        jsonObject.put("courses", courses);

        String jsonResponse = jsonObject.toString();
        resp.getWriter().write(jsonResponse);


    }

}
