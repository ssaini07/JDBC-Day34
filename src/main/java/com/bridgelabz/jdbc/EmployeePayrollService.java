package com.bridgelabz.jdbc;

import java.time.LocalDate;
import java.util.Locale;

public class EmployeePayrollService {

    EmployeePayrollRepository repository = new EmployeePayrollRepository();
    public static void main(String[] args) {
        EmployeePayrollService service = new EmployeePayrollService();
        //service.retrieveData();
       // service.updateSalary("Terrisa", 1000000);
        //service.retrieveDataByDate();
        service.getSumOfSalaryByGender();
        service.getMaxSalaryByGender();
        service.getAvgSalaryByGender();
        service.getMinSalaryByGender();
        service.getCountByGender();
    }

    private void getCountByGender() {
        System.out.println(repository.getCountByGender());
    }

    private void getMinSalaryByGender() {
        System.out.println(repository.getMinSalaryByGender());
    }

    private void getAvgSalaryByGender() {
        System.out.println(repository.getAvgSalaryByGender());
    }

    private void getMaxSalaryByGender() {
        System.out.println(repository.getMaxSalaryByGender());
    }

    private void getSumOfSalaryByGender() {
        System.out.println(repository.getSumOfSalaryByGender());
    }


    private void retrieveDataByDate() {
        System.out.println(repository.retrieveDataByParticularRange());
    }

    private void updateSalary(String name, int salary) {
        repository.updateSalaryUsingPreparedStatement(name.toLowerCase(), salary);
        //repository.updateSalary(name, salary);
    }

    private void retrieveData() {
        System.out.println(repository.retrieveData());
    }

}
