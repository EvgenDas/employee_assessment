package com.dashenckoevgeny.spring.springboot.employee_assessment.web.mappers;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.EmployeeAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.employee.EmployeeDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

  EmployeeDto toDto(Employee employee);

  List<Employee> toDto(List<Employee> employees);

  Employee toEntity(EmployeeDto dto);

}
