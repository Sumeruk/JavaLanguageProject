package org.example.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.BD.JDBC;
import org.example.BD.JDBCImpl;
import org.example.entities.Patient;
import org.example.service.PatientService;
import org.example.service.PatientServiceImpl;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class PatientServlet extends HttpServlet {
    private PatientService patientService = PatientServiceImpl.getInstance();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp){
        JDBC jdbc = JDBCImpl.getInstance();
        jdbc.connection();
        String[] parameters = new String[5];
        parameters[0] = req.getParameter("id");
        parameters[1] = req.getParameter("FIO");
        parameters[2] = req.getParameter("age");
        parameters[3] = req.getParameter("gender");
        parameters[4] = req.getParameter("department");

        patientService.add(parameters);

        //JSONObject jsonObject = new JSONObject();
        jdbc.closeConnection();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        JDBC jdbc = JDBCImpl.getInstance();
        jdbc.connection();
        List<Patient> patients = patientService.getAll();
        JSONArray jsonArray= new JSONArray();


        for (int i = 0; i < patients.size(); i++){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", patients.get(i).getPatientId());
            jsonObject.put("FIO", patients.get(i).getFIO());
            jsonObject.put("age", patients.get(i).getAge());
            jsonObject.put("gender", patients.get(i).getGender());
            jsonObject.put("department", patients.get(i).getDepartmentId());

            jsonArray.put(jsonObject);
        }

        String jsonResponse = jsonArray.toString();
        resp.getWriter().write(jsonResponse);
        jdbc.closeConnection();
    }
}
