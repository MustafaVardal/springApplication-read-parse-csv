package com.app.util;

import com.app.entity.Employee;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class EmployeeDataFilter {

    private static final double DEFAULT_SALARY_THRESHOLD = 20000.0;

    public List<Employee> filterBySalaryAbove(List<Employee> employees, double threshold) {
        return filterEmployees(employees, employee -> employee.getSalary() > threshold);
    }

    public List<Employee> filterByDefaultSalary(List<Employee> employees) {
        return filterBySalaryAbove(employees, DEFAULT_SALARY_THRESHOLD);
    }

    private List<Employee> filterEmployees(List<Employee> employees, Predicate<Employee> condition) {
        return employees.stream().filter(condition).collect(Collectors.toList());
    }
}
