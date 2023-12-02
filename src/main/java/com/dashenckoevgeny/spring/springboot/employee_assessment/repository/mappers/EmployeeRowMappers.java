package com.dashenckoevgeny.spring.springboot.employee_assessment.repository.mappers;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Role;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.SneakyThrows;

public class EmployeeRowMappers {

  @SneakyThrows
  public static Employee mapRow(ResultSet resultSet) {
    Set<Role> roles = new HashSet<>();
    while (resultSet.next()) {
      roles.add(Role.valueOf(resultSet.getString("employee_role")));
    }
    resultSet.beforeFirst();
    List<EmployeeAssessment> assessmentList = AssessmentRowMapper.mapRows(resultSet);
    resultSet.beforeFirst();
    if(resultSet.next()) {
      Employee employee = new Employee();
      employee.setId(resultSet.getInt("employee_id"));
      employee.setName(resultSet.getString("name"));
      employee.setSurname(resultSet.getString("surname"));
      employee.setManagerId(resultSet.getInt("manager_id"));
      employee.setExpertId(resultSet.getInt("expert_id"));
      employee.setRoles(roles);
      employee.setEmployeeAssessments(assessmentList);

      Timestamp timestamp = resultSet.getTimestamp("employee_date_of_next_assessment");
      if(timestamp != null) {
        employee.setDateOfNextAssessment(timestamp.toLocalDateTime());
      }
      employee.setLogin(resultSet.getString("employee_login"));
      employee.setPassword(resultSet.getString("password"));
      return employee;
    }
    return null;
  }
}
