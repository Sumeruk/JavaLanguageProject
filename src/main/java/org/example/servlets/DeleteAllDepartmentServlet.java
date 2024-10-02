package org.example.servlets;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.service.DepartmentService;
import org.example.service.DepartmentServiceImpl;

public class DeleteAllDepartmentServlet extends HttpServlet {
    DepartmentService departmentService = DepartmentServiceImpl.getInstance();
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp){
        departmentService.removeAll();
    }
}
