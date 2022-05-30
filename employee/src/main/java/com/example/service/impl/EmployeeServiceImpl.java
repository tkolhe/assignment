package com.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.EmployeeDTO;
import com.example.model.Employee;
import com.example.model.Project;
import com.example.repository.EmployeeRepository;
import com.example.repository.ProjectRepository;
import com.example.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "Employee Service")
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	ProjectRepository projectRepository;
	
	@Override
	public List<EmployeeDTO> fetchEmployeesByProjectCode(String projectCode)  throws NoSuchElementException{
		List<EmployeeDTO> employeeDTOs = new ArrayList<>();
		Optional<Project> project = projectRepository.findById(projectCode);
		if(project.isPresent()) {
			 List<Employee>employees= employeeRepository.findDistinctByProject(project.get());
			 employeeDTOs = employees.stream().map(this::convert)
						.collect(Collectors.toList());	
		}else {
			throw new NoSuchElementException("Project Not Found");
		}
		return employeeDTOs;
	}

	/**
	 * Convert Employee into EmployeeDTO
	 * @param employee
	 * @return EmployeeDTO
	 */
	private EmployeeDTO convert(Employee employee) {
		EmployeeDTO dto = modelMapper.map (employee, EmployeeDTO.class);
		dto.setDeptCode(employee.getDepartment().getDeptCode());
		dto.setEmpCode(employee.getEmployeePKId().getEmpCode());
		return dto;
	}

	/**
	 * dissociate Employee From Project
	 */
	@Transactional
	@Override
	public boolean dissociateAllEmployeeFromProject(String projectCode) throws NoSuchElementException {
		Optional<Project> project = projectRepository.findById(projectCode);
		if(project.isPresent()) {
			List<EmployeeDTO> employees= this.fetchEmployeesByProjectCode(projectCode);
			if(!employees.isEmpty()) {
				employeeRepository.deleteByProject(project.get());
				return true;
			}else {
				log.info("Employees is not registred with specified project code : " +projectCode);
				throw new NoSuchElementException("Employee Not Registered with specified project :" +projectCode);
			}
		}else {
			log.info("Project Not Found : " +projectCode);
			throw new NoSuchElementException("Project Not Found :" +projectCode);
		}
	}

	@Override
	public List<EmployeeDTO> fetchAllEmployees() {
		List<Employee> employees =(List<Employee>) employeeRepository.findAll();
		List<EmployeeDTO> employeeDTOs = employees.stream().map(this::convert)
				.collect(Collectors.toList());
		return employeeDTOs;
	}

	@Override
	public boolean dissociateEmployeeFromProject(String empCode, String projectCode) throws NoSuchElementException {
		Optional<Project> project = projectRepository.findById(projectCode);
		if(project.isPresent()) {
			 List<Employee>employees= employeeRepository.findDistinctByProject(project.get());
			if(!employees.isEmpty()) {
				List<Employee> list =employees.stream().filter(p->p.getProject().getProjectCode().equals(projectCode)).collect(Collectors.toList());
				for(Employee employee: list) {
					employeeRepository.delete(employee);
				}
				return true;
			}else {
				log.info("Emploees Not Found : " +projectCode);
				throw new NoSuchElementException("Employees Not Found For :" +projectCode);
			}
		}
		else {
			log.info("Project Not Found : " +projectCode);
			throw new NoSuchElementException("Project Not Found :" +projectCode);
		}
	}
	
}
