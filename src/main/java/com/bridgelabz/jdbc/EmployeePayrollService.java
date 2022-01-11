package com.bridgelabz.jdbc;

import java.time.LocalDate;
import java.util.Locale;

public class EmployeePayrollService {

    EmployeePayrollRepository repository = new EmployeePayrollRepository();
    public static void main(String[] args) {
        EmployeePayrollService service = new EmployeePayrollService();
        //service.retrieveData();
       // service.updateSalary("Terrisa", 1000000);
        service.retrieveDataByDate();
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
