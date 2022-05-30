package com.example.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.EmployeeDTO;
import com.example.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/employee/{projectCode}")
	public ResponseEntity<List<EmployeeDTO>> getEmployeesByProjectCode(@PathVariable String projectCode) throws NoSuchElementException {
	  List<EmployeeDTO> employees=employeeService.fetchEmployeesByProjectCode(projectCode);
	  return new ResponseEntity<>(employees,HttpStatus.OK);
	}
	
	@GetMapping("/employee")
	public ResponseEntity<List<EmployeeDTO>> getAllEmployees(){
	  List<EmployeeDTO> employees=employeeService.fetchAllEmployees();
	  return new ResponseEntity<>(employees,HttpStatus.OK);
	}
	
	@DeleteMapping("/employee/{projectCode}")
	public ResponseEntity<Void> deleteAllEmployeesAssociatedForProject(@PathVariable String projectCode) throws NoSuchElementException{
		employeeService.dissociateAllEmployeeFromProject(projectCode);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@DeleteMapping("/employee/{empCode}/{projectCode}")
	public ResponseEntity<Void> deleteAssociatedEmployeeForProject(@PathVariable String empCode, @PathVariable String projectCode) throws NoSuchElementException {
		employeeService.dissociateEmployeeFromProject(empCode, projectCode);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity <Void>handleNotFoundException(NoSuchElementException noSuchElementException) {
        return new ResponseEntity(noSuchElementException.getMessage(), HttpStatus.NOT_FOUND);
    }
}
