package com.example.model;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "department", uniqueConstraints = {@UniqueConstraint(columnNames = {"dept_code"})})
@Setter
@Getter
public class Department {
	
	
	public Department() {
		super();
	}

	
	public Department(String deptCode, String deptName) {
		super();
		this.deptCode = deptCode;
		this.deptName = deptName;
	}

	@Id
	@Column(name = "dept_code", length = 50)
	private String deptCode;
	
	@Column(name = "dept_name",length = 50)
	private String deptName;

	@OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<Project> projects;
	
}
