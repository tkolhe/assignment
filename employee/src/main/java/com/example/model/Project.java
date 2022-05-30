package com.example.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "project", uniqueConstraints = {@UniqueConstraint(name="uniqueProjectCodeDeptCode",columnNames = {"project_code", "dept_code"})})
@Setter
@Getter
public class Project {

	
	public Project() {
		super();
	}

	@Id
	@Column(name = "project_code", length = 50)
	private String projectCode;
	
	
	@ManyToOne   
	@JoinColumn(name="dept_code",nullable=false)
	private Department department;
	
	@Column(name = "name" ,length = 50)
	private String projectName;
	
}
