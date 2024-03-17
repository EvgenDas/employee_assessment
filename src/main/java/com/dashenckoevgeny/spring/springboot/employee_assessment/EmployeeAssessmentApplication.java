package com.dashenckoevgeny.spring.springboot.employee_assessment;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
@OpenAPIDefinition(servers = { @Server(url = "http://localhost:8888")})
public class EmployeeAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeAssessmentApplication.class, args);
	}

}
