package com.bridgelabz.jdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeePayrollRepository {

    public Connection getConnection() {
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
    public void updateSalary(String name, double basic_pay) {
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

    // UC6---->
    //sum of salaries by gender.
    public Map<String, Double> getSumOfSalaryByGender() {
        Map<String, Double> sumOfSalary = new HashMap<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String query = "select gender,sum(basic_pay) as sum_salary from " +
                    "employee_payroll group by gender";
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                EmployeeInfo info = new EmployeeInfo();
                String gender = String.valueOf(resultset.getString("gender").charAt(0));
                Double sum_salary = Double.valueOf(resultset.getString("sum_salary"));
                sumOfSalary.put(gender, sum_salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sumOfSalary;
    }

    // max salary by gender
    public Map<String, Double> getMaxSalaryByGender() {
        Map<String, Double> maxSalary = new HashMap<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String query = "select gender,max(basic_pay) as max_salary from employee_payroll group by gender";
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                EmployeeInfo info = new EmployeeInfo();
                String gender = String.valueOf(resultset.getString("gender").charAt(0));
                Double max_salary = Double.valueOf(resultset.getString("max_salary"));
                maxSalary.put(gender, max_salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return maxSalary;
    }

    //avg salary by gender
    public Map<String, Double> getAvgSalaryByGender() {
        Map<String, Double> avgSalary = new HashMap<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String query = "select gender,avg(basic_pay) as avg_salary from employee_payroll group by gender";
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                EmployeeInfo info = new EmployeeInfo();
                String gender = String.valueOf(resultset.getString("gender").charAt(0));
                Double max_salary = Double.valueOf(resultset.getString("avg_salary"));
                avgSalary.put(gender, max_salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return avgSalary;
    }

    // min salary by gender
    public Map<String, Double> getMinSalaryByGender() {
        Map<String, Double> minSalary = new HashMap<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String query = "select gender,min(basic_pay) as min_salary from employee_payroll group by gender";
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                EmployeeInfo info = new EmployeeInfo();
                String gender = String.valueOf(resultset.getString("gender").charAt(0));
                Double max_salary = Double.valueOf(resultset.getString("min_salary"));
                minSalary.put(gender, max_salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return minSalary;
    }

    //count by gender
    public Map<String, Double> getCountByGender() {
        Map<String, Double> count = new HashMap<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String query = "select gender,count(basic_pay) as count from employee_payroll group by gender";
            ResultSet resultset = statement.executeQuery(query);
            while (resultset.next()) {
                EmployeeInfo info = new EmployeeInfo();
                String gender = String.valueOf(resultset.getString("gender").charAt(0));
                Double max_salary = Double.valueOf(resultset.getString("count"));
                count.put(gender, max_salary);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    //UC7 ------>

    public List<EmployeeInfo> addNewEmployee() {
        List<EmployeeInfo> employeeInfos = new ArrayList<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            String sqlQuery = "insert into employee_payroll\n" +
                    "(name, gender, startDate, address, phone_number)\n" +
                    "values ('Sara', 'F', '2021-01-19', 'colony no:4', '9898989899');";
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


}


