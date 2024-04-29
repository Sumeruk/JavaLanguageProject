package org.example.repository;

import org.example.BD.JDBC;
import org.example.BD.JDBCImpl;
import org.example.Help.Gender;
import org.example.entities.Patient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PatientRepositoryJDBCImpl implements PatientRepository {
    private static PatientRepository object;
    private static final JDBC jdbc = JDBCImpl.getInstance();

    private PatientRepositoryJDBCImpl() {
    }

    public static PatientRepository getInstance() {
        if (object == null) {
            object = new PatientRepositoryJDBCImpl();
        }
        return object;
    }
    private Patient setInfoFromResultSetToPatient(ResultSet res){
        try {
            Patient patient = new Patient();
            patient.setPatientId(res.getInt(1));
            patient.setFIO(res.getString(2));
            patient.setAge(res.getInt(3));
            patient.setGender(Gender.valueOf(res.getString(4)));
            patient.setDepartmentId(res.getInt(5));
            return patient;
        } catch (SQLException sqlE){
            System.out.println(sqlE.getMessage());
        }
        return null;
    }
    private Patient getPatientFromSQL(ResultSet res){
        try {
            if (res.next()) {
                return setInfoFromResultSetToPatient(res);
            }
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
        }

        return null;

    }

    private List<Patient> getPatientsFromSQL(ResultSet res){
        List<Patient> patients = new ArrayList<>();
        try {
            while (res.next()) {
                patients.add(setInfoFromResultSetToPatient(res));
            }
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
        }

        return patients;

    }

    @Override
    public List<Patient> getPatientsByDepartmentId(int departmentID) {
        try {
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "Select * from patient where " + "department_id " + "=" + departmentID + ";";
            ResultSet res = statement.executeQuery(sql);
            return getPatientsFromSQL(res);
        } catch (SQLException sqlE) {
            System.out.println("ошибка при выполнении запроса");
        }
        return null;
    }

    @Override
    public void update(int id, Patient patient) {
        try{
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "update patient set patient_id = " +
                    patient.getPatientId() +
                    ", fio = '" + patient.getFIO() +
                    "', age = " + patient.getAge() +
                    ", gender = '" + patient.getGender().toString() +
                    "', department_id = " + patient.getDepartmentId() +
                    " where patient_id = " + id;
            int res = statement.executeUpdate(sql);
            System.out.println("обновлено " + res);
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
            System.out.println(sqlE.getMessage());
        }
    }

    @Override
    public void updateDepartmentId(int oldDepId, int newDepId){
        try{
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "update patient set department_id = " +
                    newDepId +
                    " where patient_id = " + oldDepId;
            int res = statement.executeUpdate(sql);
            System.out.println("обновлено " + res);
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
            System.out.println(sqlE.getMessage());
        }
    }

    @Override
    public void deletePatientsByDepartmentId(int departmentId) {
        try{
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "DELETE FROM patient WHERE department_id = " + departmentId;
            int res = statement.executeUpdate(sql);
            System.out.println("удалено " + res);
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
            System.out.println(sqlE.getMessage());
        }
    }


    @Override
    public void add(Patient object) {
        try{
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "insert into patient values (" + object.getPatientId()
                    + ", '" + object.getFIO() + "', "  + object.getAge() +
                    ", '" + object.getGender().toString() + "', " + object.getDepartmentId() + ");";
            int res = statement.executeUpdate(sql);
            System.out.println("добавлено " + res);
        } catch (SQLException sqlE){
            System.out.println("ошибка при выполнении запроса");
        }
    }

    @Override
    public void remove(int id) {
        try {
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "DELETE FROM patient WHERE patient_id = " + id;
            int res = statement.executeUpdate(sql);
            System.out.println("удалено " + res);
        } catch (SQLException sqlE) {
            System.out.println("ошибка при выполнении запроса");
            System.out.println(sqlE.getMessage());
        }
    }
    @Override
    public void removeAll() {
        try {
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "DELETE FROM patient";
            int res = statement.executeUpdate(sql);
            System.out.println("удалено " + res);
        } catch (SQLException sqlE) {
            System.out.println("ошибка при выполнении запроса");
            System.out.println(sqlE.getMessage());
        }
    }

    @Override
    public Patient getById(int id) {
        try {
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "Select * from patient where patient_id = " + id;
            ResultSet res = statement.executeQuery(sql);
            return getPatientFromSQL(res);
        } catch (SQLException sqlE) {
            System.out.println("ошибка при выполнении запроса");
        }
        return null;
    }

    @Override
    public List<Patient> getAll() {
        try {
            Statement statement = jdbc.getConnection().createStatement();
            String sql = "Select * from patient";
            ResultSet res = statement.executeQuery(sql);
            return getPatientsFromSQL(res);
        } catch (SQLException sqlE) {
            System.out.println("ошибка при выполнении запроса");
        }
        return null;
    }
}
