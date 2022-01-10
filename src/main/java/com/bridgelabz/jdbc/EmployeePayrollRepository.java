package com.bridgelabz.jdbc;

import java.sql.*;
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
                info.setStartDate(resultSet.getDate("startDate").toLocalDate());
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
