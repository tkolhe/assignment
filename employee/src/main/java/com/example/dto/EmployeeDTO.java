package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
	
	private String empCode;
	private String deptCode;
	private String projectCode;
	private String firstName;
	private String lastName;
	
	@Override
	public String toString() {
		return "EmployeeDTO [empCode=" + empCode + ", deptCode=" + deptCode + ", projectCode=" + projectCode
				+ ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
}
