package com.dashenckoevgeny.spring.springboot.employee_assessment.repository;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeAssessmentRepository {

  Optional<EmployeeAssessment> findById(Integer id);

  List<EmployeeAssessment> findAllByEmployeeId(Integer id);

  void assignToEmployeeById(Integer assessmentId, Integer employeeId);

  void update(EmployeeAssessment assessment);

  void create(EmployeeAssessment assessment);

  void delete(Integer id);

}
