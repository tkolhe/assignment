package com.example.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="employee")
@Data
@NoArgsConstructor
public class Employee {
	
	@EmbeddedId
	private EmployeePKId employeePKId;
	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@ManyToOne
	@MapsId("dept_code")
	@JoinColumn(name="dept_code")
	private Department department;
	
	@ManyToOne
	@MapsId("project_code")
	@JoinColumn(name="project_code")
	private Project project;
}
