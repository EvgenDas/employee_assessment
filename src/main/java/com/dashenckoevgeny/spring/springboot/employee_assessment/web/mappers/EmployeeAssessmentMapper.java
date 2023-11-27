package com.dashenckoevgeny.spring.springboot.employee_assessment.web.mappers;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.EmployeeAssessmentDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeAssessmentMapper {

  EmployeeAssessmentDto toDto(EmployeeAssessment assessment);

  List<EmployeeAssessmentDto> toDto(List<EmployeeAssessment> assessments);

  EmployeeAssessment toEntity(EmployeeAssessmentDto dto);

}
