package com.example.service;

import java.util.List;

import com.example.dto.EmployeeDTO;

public interface EmployeeService {
	public  List<EmployeeDTO> fetchEmployeesByProjectCode(String projectCode);
	
	public boolean dissociateAllEmployeeFromProject(String projectCode);
	
	public boolean dissociateEmployeeFromProject(String empCode, String projectCode);
	
	public List<EmployeeDTO> fetchAllEmployees();
	
}
