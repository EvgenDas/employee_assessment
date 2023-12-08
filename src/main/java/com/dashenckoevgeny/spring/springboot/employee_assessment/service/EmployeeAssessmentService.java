package com.dashenckoevgeny.spring.springboot.employee_assessment.service;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.OwnAssessmentDto;
import java.util.List;

public interface EmployeeAssessmentService {

  EmployeeAssessment getById(Integer id);

  List<EmployeeAssessment> getAllByEmployeeId(Integer id);

  EmployeeAssessment update(EmployeeAssessment assessment);

  EmployeeAssessment create(Integer employeeId, EmployeeAssessment assessment);

  OwnAssessmentDto updateOwnAssessment(OwnAssessmentDto assessment);

  void delete(Integer id);

}
