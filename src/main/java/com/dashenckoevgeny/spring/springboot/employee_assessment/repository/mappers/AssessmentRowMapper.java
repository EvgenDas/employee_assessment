package com.dashenckoevgeny.spring.springboot.employee_assessment.repository.mappers;


import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.SneakyThrows;

public class AssessmentRowMapper {
  @SneakyThrows
  public static EmployeeAssessment mapRow(ResultSet resultSet) {
    if(resultSet.next()) {
      EmployeeAssessment employeeAssessment = new EmployeeAssessment();
      employeeAssessment.setId(resultSet.getInt("assessment_id"));
      employeeAssessment.setOwnAssessment(resultSet.getString("own_assessment"));
      employeeAssessment.setExpertAssessment(resultSet.getString("expert_assessment"));
      employeeAssessment.setManagerAssessment(resultSet.getString("manager_assessment"));
      employeeAssessment.setFinalAssessment(resultSet.getString("final_assessment"));
      Timestamp timestamp = resultSet.getTimestamp("date_of_assessment");
      if(timestamp != null) {
        employeeAssessment.setDateOfAssessment(resultSet.getTimestamp("date_of_assessment").toLocalDateTime());
      }
      employeeAssessment.setActive(resultSet.getBoolean("is_active"));
      return employeeAssessment;
    }
    return null;
  }

  @SneakyThrows
  public static List<EmployeeAssessment> mapRows(ResultSet resultSet) {
    List<EmployeeAssessment> assessments = new ArrayList<>();
    while (resultSet.next()) {
      EmployeeAssessment employeeAssessment = new EmployeeAssessment();
      employeeAssessment.setId(resultSet.getInt("assessment_id"));
      if(!resultSet.wasNull()) {
        employeeAssessment.setOwnAssessment(resultSet.getString("own_assessment"));
        employeeAssessment.setExpertAssessment(resultSet.getString("expert_assessment"));
        employeeAssessment.setManagerAssessment(resultSet.getString("manager_assessment"));
        employeeAssessment.setFinalAssessment(resultSet.getString("final_assessment"));
        Timestamp timestamp = resultSet.getTimestamp("date_of_assessment");
        if(timestamp != null) {
          employeeAssessment.setDateOfAssessment(resultSet.getTimestamp("date_of_assessment").toLocalDateTime());
        }
        employeeAssessment.setActive(resultSet.getBoolean("is_active"));
        assessments.add(employeeAssessment);
      }
    }
    return assessments;
  }

}
