package com.dashenckoevgeny.spring.springboot.employee_assessment.service;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import java.util.List;

public interface EmployeeAssessmentService {

  EmployeeAssessment getById(Integer id);

  List<EmployeeAssessment> getAllByEmployeeId(Integer id);

  EmployeeAssessment update(EmployeeAssessment assessment);

  EmployeeAssessment create(Integer employeeId, EmployeeAssessment assessment);

  void delete(Integer id);

}
