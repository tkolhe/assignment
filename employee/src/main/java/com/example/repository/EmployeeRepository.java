package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Employee;
import com.example.model.EmployeePKId;
import com.example.model.Project;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, EmployeePKId>{

	List<Employee> findDistinctByProject(@Param("project") Project project);
	
	
	void deleteByProject(@Param("project") Project project);
}
