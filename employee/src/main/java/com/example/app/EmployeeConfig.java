package com.example.app;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
