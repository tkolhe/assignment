package com.example.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class EmployeePKId implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "emp_code")
	private String empCode;
	
	@Column(name = "dept_code")
	private String deptCode;
	
	@Column(name = "project_code")
	private String projectCode;
	
}
