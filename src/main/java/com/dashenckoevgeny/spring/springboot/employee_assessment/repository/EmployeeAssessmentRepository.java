package com.dashenckoevgeny.spring.springboot.employee_assessment.repository;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeAssessmentRepository {

  Optional<EmployeeAssessment> findById(Integer id);

  List<EmployeeAssessment> findAllByEmployeeId(Integer id);

  void assignToEmployeeById(@Param("employeeId") Integer employeeId, @Param("assessmentId") Integer assessmentId);

  void update(EmployeeAssessment assessment);

  void create(EmployeeAssessment assessment);

  void delete(Integer id);

}
