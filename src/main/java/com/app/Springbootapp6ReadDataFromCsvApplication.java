package com.app;

import com.app.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Springbootapp6ReadDataFromCsvApplication implements CommandLineRunner {

	private final EmployeeService employeeService;

	@Autowired
	public Springbootapp6ReadDataFromCsvApplication(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Springbootapp6ReadDataFromCsvApplication.class, args);
	}

	@Override
	public void run(String... args) {
		String fileName = (args.length > 0) ? args[0] : "employees.csv";
		employeeService.processEmployeeData(fileName);
	}
}
