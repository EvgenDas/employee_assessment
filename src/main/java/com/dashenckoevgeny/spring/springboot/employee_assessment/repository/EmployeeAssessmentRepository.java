package com.dashenckoevgeny.spring.springboot.employee_assessment.repository;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.ExpertAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.ManagerAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.OwnAssessmentDto;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeAssessmentRepository {

  Optional<EmployeeAssessment> findById(Integer id);

  Optional<String> findOwnAssessmentById(Integer id);

  Optional<Map<String, Integer>> findManagerAssessmentById(Integer id);

  Optional<Map<String, Integer>> findExpertAssessmentById(Integer id);

  Optional<Map<String, Integer>> findFinalAssessmentById(Integer id);

  List<EmployeeAssessment> findAllByEmployeeId(Integer id);

  void assignToEmployeeById(@Param("employeeId") Integer employeeId, @Param("assessmentId") Integer assessmentId);

  void update(EmployeeAssessment assessment);

  void create(EmployeeAssessment assessment);

  void delete(Integer id);

  void updateOwnAssessment(OwnAssessmentDto assessment);

  void updateManagerAssessment(ManagerAssessmentDto assessment);
  void updateExpertAssessment(ExpertAssessmentDto assessment);

}
