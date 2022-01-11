package com.bridgelabz.jdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EmployeePayrollRepository {

    private Connection getConnection() {
        Connection connection = null;
        try {
            String JDBCURL = "jdbc:mysql://localhost:3306/employee_payroll_service";
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBCURL, "root", "suzuki@7celerio");

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Sorry Driver is not loaded!!");
        }
        return connection;
    }

    public List<EmployeeInfo> retrieveData() {
        List<EmployeeInfo> employeeInfos = new ArrayList<>();
        try (Connection connection = getConnection()) {

            //Step:3
            Statement statement = connection.createStatement();
            String sqlQuery = "select * from employee";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                EmployeeInfo info = new EmployeeInfo();
                info.setId(resultSet.getInt("id"));
                info.setName(resultSet.getString("name"));
                info.setGender(resultSet.getString("gender").charAt(0));
                info.setStartDate(Date.valueOf(resultSet.getDate("startDate").toLocalDate()));
                info.setAddress(resultSet.getString("address"));
                info.setPhone(resultSet.getString("phone_number"));
                employeeInfos.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeInfos;
    }

    //Update salary of employee
    public void updateSalary(String name, int basic_pay) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            // String sqlQuery = "update employee set salary = " + salary + "where name = '" + name + "'";
            String sqlQuery = String.format("update employee_payroll set basic_pay = %d where name = '%s'", basic_pay, name);
            int result = statement.executeUpdate(sqlQuery);
            if (result >= 1) {
                System.out.println("salary updated");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSalaryUsingPreparedStatement(String name, int salary) {
        try (Connection connection = getConnection()) {
            String query = "update employee set salary=? where name =?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, salary);
            preparedStatement.setString(2, name);
            int result = preparedStatement.executeUpdate();
            if (result >= 1) {
                System.out.println("salary updated");
            }

        } catch (SQLException e) {
        }
    }

    public List<EmployeeInfo> retrieveDataByParticularRange() {
        List<EmployeeInfo> employeeInfos = new ArrayList<>();
        try (Connection connection = getConnection()) {

            String sqlQuery = "select * from employee\n" +
                    "where startDate between '2021-04-10' and date(now());\n";
            Statement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                EmployeeInfo info = new EmployeeInfo();
                info.setId(resultSet.getInt("id"));
                info.setName(resultSet.getString("name"));
                info.setGender(resultSet.getString("gender").charAt(0));
                info.setStartDate(Date.valueOf(resultSet.getDate("startDate").toLocalDate()));
                info.setAddress(resultSet.getString("address"));
                info.setPhone(resultSet.getString("phone_number"));
                employeeInfos.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeInfos;
    }
}


