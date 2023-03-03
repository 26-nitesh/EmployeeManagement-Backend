package com.backend.employee.api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.backend.employee.api.entites.Employee;
import com.backend.employee.api.exceptions.ConflictException;
import com.backend.employee.api.repositories.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveNewEmployee(Employee employee) {
		boolean empAlreadyExist = isAlreadyExist(employee.getEmpEmail());
		if(!empAlreadyExist) {
			return saveEmp(employee);
		}else if(empAlreadyExist)
			throw new ConflictException();
		return null;
	}

	private Employee saveEmp(Employee employee) {
		return employeeRepository.save(employee);
	}

	private boolean isAlreadyExist(String email) {
		return employeeRepository.findByEmpEmail(email).isPresent();
	}

	@Override
	public List<Employee> findAllemployees() {
		return employeeRepository.findAll();
	}


	@Override
	public void deleteEmployee(String email) {
		
		boolean alreadyExist = 
				isAlreadyExist(email);
		
		if(alreadyExist)
			deleteEmployeeByEmail(email);
		else if(!alreadyExist)
			throw new ConflictException();
		
	}

	private void deleteEmployeeByEmail(String email) {
		employeeRepository.
        deleteByEmail(email);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		
		boolean alreadyExist = 
				isAlreadyExist(employee.getEmpEmail());
		if(alreadyExist) {
			deleteEmployee(employee.getEmpEmail());
			saveEmp(employee);
		}else if(!alreadyExist)
			throw new ConflictException();
	}
	
	
	
}
