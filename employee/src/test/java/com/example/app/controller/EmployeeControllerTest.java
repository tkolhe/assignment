package com.example.app.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.dto.EmployeeDTO;
import com.example.exception.NoSuchElementFoundException;
import com.example.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;


@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(classes = com.example.app.EmployeeApplication.class)
public class EmployeeControllerTest {
	
	private MockMvc mockMvc;
	
	
	@Autowired
	private WebApplicationContext context;
	
	@MockBean
	EmployeeService employeeService;
	
	EmployeeDTO RECORD_1 = new EmployeeDTO("E101","D101","P101","Tanuja","Kolhe");
	EmployeeDTO RECORD_2 = new EmployeeDTO("E101","D101","P102","Tanuja","Kolhe");
	EmployeeDTO RECORD_3 = new EmployeeDTO("E102","D102","P103","Tanuja","Kolhe");
	@BeforeAll
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		assertNotNull(mockMvc);
	}
	
	@Test
	public void getAllEmployees_success() throws Exception {
	    List<EmployeeDTO> records = new ArrayList<>(Arrays.asList(RECORD_1, RECORD_2, RECORD_3));
	    
	    Mockito.when(employeeService.fetchAllEmployees()).thenReturn(records);
	    
	    mockMvc.perform(MockMvcRequestBuilders
	            .get("/employee")
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	@Test
	void getEmployeesByProjectCode_sucess() throws Exception {
		
		ResultActions ra = mockMvc.perform(MockMvcRequestBuilders.get("/employee/"+RECORD_1.getProjectCode()));
		ra.andExpect(status().is(200));
		
	}
	
	@Test
	void getEmployeesByProjectCode_notFoundException() throws Exception {
		String projectCode ="P105";
		Mockito.when(employeeService.fetchEmployeesByProjectCode(projectCode)).thenThrow(NoSuchElementFoundException.class);
		ResultActions ra = mockMvc.perform(MockMvcRequestBuilders.get("/employee/"+projectCode));
		ra.andExpect(status().isNotFound());
		
	}
	
	@Test
	public void deleteEmployeeForProjectCode_success() throws Exception {
		List<EmployeeDTO> list = new ArrayList<>(Arrays.asList(RECORD_1));
	    Mockito.when(employeeService.fetchEmployeesByProjectCode(RECORD_1.getProjectCode())).thenReturn(list);

	    mockMvc.perform(MockMvcRequestBuilders
	            .delete("/employee/"+RECORD_1.getProjectCode())
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk());
	}
	
	
}
