package com.app.service;

import com.app.dao.CSVDataReaderDao;
import com.app.dao.CSVDataWriterDao;
import com.app.entity.Employee;
import com.app.util.EmployeeDataFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final CSVDataReaderDao csvDataReaderDao;
    private final EmployeeDataFilter employeeDataFilter;
    private final CSVDataWriterDao csvDataWriterDao;

    public EmployeeService(CSVDataReaderDao csvDataReaderDao, EmployeeDataFilter employeeDataFilter, CSVDataWriterDao csvDataWriterDao) {
        this.csvDataReaderDao = csvDataReaderDao;
        this.employeeDataFilter = employeeDataFilter;
        this.csvDataWriterDao = csvDataWriterDao;
    }

    public void processEmployeeData(String fileName) {  // <-- Eski adı: doProcess
        List<Employee> employees = csvDataReaderDao.readCSVFileData(fileName);
        printEmployees("All Employees:", employees);

        List<Employee> filteredEmployees = employeeDataFilter.filterByDefaultSalary(employees);
        printEmployees("Filtered Employees:", filteredEmployees);

        saveFilteredEmployees(filteredEmployees);
    }

    private void saveFilteredEmployees(List<Employee> employees) {
        String status = csvDataWriterDao.writeCSVFileData(employees);
        System.out.println(status);
    }

    private void printEmployees(String message, List<Employee> employees) {
        System.out.println("\n" + message);
        employees.forEach(System.out::println);
    }
}
