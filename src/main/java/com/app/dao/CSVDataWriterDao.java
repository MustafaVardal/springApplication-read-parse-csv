package com.app.dao;

import com.app.entity.Employee;
import org.springframework.stereotype.Repository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Repository
public class CSVDataWriterDao {

    private static final String CSV_FILE_NAME = "filtered-employee-data.csv";

    public String writeCSVFileData(List<Employee> employees) {
        try (FileWriter fileWriter = new FileWriter(CSV_FILE_NAME)) {
            for (Employee employee : employees) {
                fileWriter.write(formatEmployeeData(employee));
            }
            return "Data stored in CSV file successfully: " + CSV_FILE_NAME;
        } catch (IOException e) {
            throw new RuntimeException("Error writing to CSV file: " + CSV_FILE_NAME, e);
        }
    }

    private String formatEmployeeData(Employee employee) {
        return employee.getEmpId() + "," + employee.getName() + "," + employee.getSalary() + "\n";
    }
}
