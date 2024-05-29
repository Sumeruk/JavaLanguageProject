package org.example.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.BD.JDBC;
import org.example.BD.JDBCImpl;
import org.example.entities.Department;
import org.example.entities.Patient;
import org.example.service.DepartmentService;
import org.example.service.DepartmentServiceImpl;
import org.example.service.PatientService;
import org.example.service.PatientServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class DepartmentServlet extends HttpServlet {

    private DepartmentService departmentService = DepartmentServiceImpl.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        JDBC jdbc = JDBCImpl.getInstance();
        jdbc.connection();

        String[] parameters = new String[2];
        parameters[0] = req.getParameter("id");
        parameters[1] = req.getParameter("name");

        departmentService.add(parameters);

        jdbc.closeConnection();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JDBC jdbc = JDBCImpl.getInstance();
        jdbc.connection();

        List<Department> departments = departmentService.getAll();
        System.out.println(departments);
        JSONArray jsonArray= new JSONArray();

        for (int i = 0; i < departments.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", departments.get(i).getId());
            jsonObject.put("name", departments.get(i).getName());
            jsonArray.put(jsonObject);

        }

        String jsonResponse = jsonArray.toString();
        resp.getWriter().write(jsonResponse);

        jdbc.closeConnection();
    }
}
