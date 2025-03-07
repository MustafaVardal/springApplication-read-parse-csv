package com.app.dao;

import com.app.entity.Employee;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRecord;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CSVDataReaderDao {

    private static final String DELIMITER = ","; // CSV delimiter

    public List<Employee> readCSVFileData(String fileName) {
        try(CsvReader<CsvRecord> csv= CsvReader.builder().ofCsvRecord(fileName)){
            csv.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        List<Employee> employees = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                Employee employee = parseEmployee(line);
                if (employee != null) {
                    employees.add(employee);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("File Reader Error: " + fileName, e);
        }

        return employees;
    }

    private Employee parseEmployee(String line) {
        String[] data = line.split(DELIMITER);

        if (data.length < 3) {
            System.err.println("CSV Format Error: " + line);
            return null;
        }

        try {
            int id = Integer.parseInt(data[0].trim());
            String name = data[1].trim();
            double salary = Double.parseDouble(data[2].trim());

            Employee employee = new Employee();
            employee.setEmpId(id);
            employee.setName(name);
            employee.setSalary(salary);

            return employee;
        } catch (NumberFormatException e) {
            System.err.println("Data Parse Error: " + line);
            return null;
        }
    }
}
