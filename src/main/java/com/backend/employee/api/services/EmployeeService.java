package com.backend.employee.api.services;

import org.springframework.stereotype.Service;

import com.backend.employee.api.entites.Employee;

public interface EmployeeService {

	Employee saveNewEmployee(Employee employee);

	java.util.List<Employee> findAllemployees();

	void deleteEmployee(String email);

	void updateEmployee(Employee employee);

	
}
