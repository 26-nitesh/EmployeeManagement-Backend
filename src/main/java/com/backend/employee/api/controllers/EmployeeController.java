package com.backend.employee.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.employee.api.entites.Employee;
import com.backend.employee.api.exceptions.ConflictException;
import com.backend.employee.api.services.EmployeeService;
import com.backend.employee.apiResponse.APIResponse;


@RestController
@RequestMapping("api/emp/")
public class EmployeeController {
	
	private static final String ERR_OCCURED="Error  Occured";

	@Autowired
   private EmployeeService employeeService;
	
	@PostMapping("/save")
	public ResponseEntity<Object> saveEmp(@RequestBody Employee employee) {
		try {
			Employee savedEmployee = 
					employeeService.saveNewEmployee(employee);
			
            return APIResponse.
            		generateResponse(
            				HttpStatus.CREATED.name(),
            				HttpStatus.CREATED,
            				savedEmployee);
            
		} catch (ConflictException e) {
			return APIResponse.
					generateResponse(
							"email Already exist",
							HttpStatus.CONFLICT,
							null);
			
		}catch (Exception e) {
			return APIResponse.
					generateResponse(
							ERR_OCCURED,
							HttpStatus.BAD_REQUEST,
							null);
		}
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Employee>> getAllEmployees(){
		return 
				new ResponseEntity<List<Employee>>(
						employeeService.findAllemployees(),
						HttpStatus.FOUND
						);
	}
	
	@DeleteMapping("/{email}")
	public ResponseEntity<?> deleteByEmail(@PathVariable String email){
		try {
			employeeService.deleteEmployee(email);

            return APIResponse.
            		generateResponse(
            				"Deleted Suseccefully ",
            				HttpStatus.ACCEPTED,
            				email);
		} catch (ConflictException e) {

            return APIResponse.
            		generateResponse(
            				"employee with email : "+email+" not present",
            				HttpStatus.NOT_FOUND,
            				null);
		}catch (Exception e) {
			return APIResponse.
					generateResponse(
							ERR_OCCURED,
							HttpStatus.BAD_REQUEST,
							null);
		}
		
	}
	
	@PutMapping("/")
	public ResponseEntity<?> updateByEmail(@RequestBody Employee employee){
		try {
			employeeService.updateEmployee(employee);

            return APIResponse.
            		generateResponse(
            				"Updated Suseccefully ",
            				HttpStatus.ACCEPTED,
            				employee);
		} catch (ConflictException e) {

            return APIResponse.
            		generateResponse(
            				"employee with email : "+employee.getEmpEmail()+" not present",
            				HttpStatus.NOT_FOUND,
            				null);
		}catch (Exception e) {
			return APIResponse.
					generateResponse(
							ERR_OCCURED,
							HttpStatus.BAD_REQUEST,
							null);
		}
		
	}
	
}
