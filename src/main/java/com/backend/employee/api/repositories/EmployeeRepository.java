package com.backend.employee.api.repositories;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.backend.employee.api.entites.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	Optional<Employee> findByEmpEmail(String email);
	
	@Query(value = "delete  from employee e where e.emp_email =:empEmail",nativeQuery =true)
	@Transactional
	@Modifying
	void deleteByEmail(String empEmail);
}
