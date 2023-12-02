package com.dashenckoevgeny.spring.springboot.employee_assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class EmployeeAssessmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeAssessmentApplication.class, args);
	}

}
