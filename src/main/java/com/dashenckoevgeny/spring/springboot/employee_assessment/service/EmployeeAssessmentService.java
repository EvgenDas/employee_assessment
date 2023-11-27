package com.dashenckoevgeny.spring.springboot.employee_assessment.service;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import java.util.List;

public interface EmployeeAssessmentService {

  EmployeeAssessment getById(Integer id);

  List<EmployeeAssessment> getAllByEmployeeId(Integer id);

  EmployeeAssessment update(EmployeeAssessment assessment);

  EmployeeAssessment create(EmployeeAssessment assessment, Integer id);

  EmployeeAssessment delete(Integer id);

}