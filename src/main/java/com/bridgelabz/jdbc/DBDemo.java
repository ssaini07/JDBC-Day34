package com.bridgelabz.jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBDemo {

    public static void main(String[] args) {
        //System.out.println("Welcome to JDBC Demo!!!");
        //Step:-1
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver is loaded successfully!!");
        } catch (ClassNotFoundException e) {
            System.out.println("Sorry Driver is not loaded!!");
        }

        //Step:-2(make a connection)
        // String JDBCURL = "jdbc:mysql://127.0.0.1:3306/employee_payroll_service";
        String JDBCURL = "jdbc:mysql://localhost:3306/employee_payroll_service";
        try {
            DriverManager.getConnection(JDBCURL, "root", "suzuki@7celerio");
            System.out.println("Connection established successfully!!");
        } catch (SQLException e) {
            System.out.println("Connection is not established");
        }

    }
}
