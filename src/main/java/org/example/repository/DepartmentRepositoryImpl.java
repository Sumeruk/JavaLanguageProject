package org.example.repository;

import org.example.BD.JDBC;
import org.example.BD.JDBCImpl;
import org.example.entities.Department;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepositoryImpl implements DepartmentRepository {

    private static DepartmentRepository object;
    private static PatientRepository patientRepository;
    private static JDBC jdbc = JDBCImpl.getInstance();

    public DepartmentRepositoryImpl() {
    }

    public static DepartmentRepository getInstance() {
        if (object == null) {
            object = new DepartmentRepositoryImpl();
        }
        return object;
    }

    private Department getDepartmentFromSQL(ResultSet res){
        try {
            if (res.next()) {
                Department department = new Department();
                department.setId(res.getInt(1));
                department.setName(res.getString(2));
                return department;
            }
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
        }

        return null;

    }

    private List<Department> getDepartmentsFromSQL(ResultSet res){
        List<Department> departments = new ArrayList<>();
        try {
            while (res.next()) {
                Department department = new Department();
                department.setId(res.getInt(1));
                department.setName(res.getString(2));
                departments.add(department);
            }
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
        }

        return departments;

    }

    @Override
    public Department getDepartmentByName(String name) {
        try{
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "Select * from department where name = '" + name + "';";
            ResultSet res = statement.executeQuery(sql);
            return getDepartmentFromSQL(res);
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
        }

        return null;
    }

    @Override
    public void add(Department object) {
        try{
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "insert into department values (" + object.getId()
                    + ", '" + object.getName() + "');";
            int res = statement.executeUpdate(sql);
            System.out.println("добавлено " + res);
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
        }
    }

    @Override
    public void remove(int id) {
        try{
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "DELETE from department where id =" + id + ";";
            int res = statement.executeUpdate(sql);
            System.out.println("удалено " + res);
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
        }
    }

    @Override
    public void removeAll() {
        try{
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "DELETE from department;";
            int res = statement.executeUpdate(sql);
            System.out.println("удалено " + res);
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
        }
    }


    @Override
    public void update(int id, Department newObject) {
        try{
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "update department set id = "+ newObject.getId()
                    + ", name = " + newObject.getName() + " where id = " + id;
            int res = statement.executeUpdate(sql);
            System.out.println("обновлено " + res);
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
        }
    }

    @Override
    public Department getById(int id) {
        try{
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "Select * from department where id = " + id + ";";
            ResultSet res = statement.executeQuery(sql);
            return getDepartmentFromSQL(res);
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
        }
        return null;
    }


    @Override
    public List<Department> getAll() {
        try{
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "Select * from department;";
            ResultSet res = statement.executeQuery(sql);
            return getDepartmentsFromSQL(res);
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
        }
        return null;
    }
}
