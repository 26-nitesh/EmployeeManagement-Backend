package com.backend.employee.api.entites;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Employee {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String empId;
	private String empName;
	private String empEmail;
	private String designation;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public Employee(int id, String empId, String empName, String empEmail, String designation) {
		super();
		this.id = id;
		this.empId = empId;
		this.empName = empName;
		this.empEmail = empEmail;
		this.designation = designation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpEmail() {
		return empEmail;
	}

	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@Override
	public String toString() {
		return "Emploee [id=" + id + ", empId=" + empId + ", empName=" + empName + ", empEmail=" + empEmail
				+ ", designation=" + designation + "]";
	}
	
	

}
