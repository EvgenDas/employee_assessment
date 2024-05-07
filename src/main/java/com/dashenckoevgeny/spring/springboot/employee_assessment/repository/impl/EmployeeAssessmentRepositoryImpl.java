package com.dashenckoevgeny.spring.springboot.employee_assessment.repository.impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.exception.ResourceMappingException;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.DataSourceConfig;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.EmployeeAssessmentRepository;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.mappers.AssessmentRowMapper;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.ExpertAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.ManagerAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.OwnAssessmentDto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

//@Repository
@RequiredArgsConstructor
public class EmployeeAssessmentRepositoryImpl implements EmployeeAssessmentRepository {

  private final DataSourceConfig dataSourceConfig;

  private final String FIND_BY_ID = """
      select t.id as assessment_id,
        t.own_assessment as own_assessment,
        t.expert_assessment as expert_assessment,
        t.manager_assessment as manager_assessment,
        t.final_assessment as final_assessment,
        t.date_of_assessment as date_of_assessment,
        t.is_active as is_active
      from assessments t
      where t.id = ?
      """;

  private final String FIND_BY_ID_OWN_ASSESSMENT = """
      select t.assessment as assessment,
      from matrix m
      where m.assessment_id = ?
      and m.who = 'own'
      """;

  private final String FIND_BY_ID_MANAGER_ASSESSMENT = """
      select t.assessment as assessment,
      from matrix m
      where m.assessment_id = ?
      and m.who = 'manager'
      """;

  private final String FIND_BY_ID_EXPERT_ASSESSMENT = """
      select t.assessment as assessment,
      from matrix m
      where m.assessment_id = ?
      and m.who = 'expert'
      """;

  private final String FIND_BY_ID_FINAL_ASSESSMENT = """
      select t.assessment as assessment,
      from matrix m
      where m.assessment_id = ?
      and m.who = 'final'
      """;


  private final String FIND_ALL_BY_EMPLOYEE_ID = """
      select t.id as assessment_id,
        t.own_assessment as own_assessment,
        t.expert_assessment as expert_assessment,
        t.manager_assessment as manager_assessment,
        t.final_assessment as final_assessment,
        t.date_of_assessment as date_of_assessment,
        t.is_active as is_active
      from assessments t
        join employees_assessments ea on t.id = ea.assessment_id
      where ea.employee_id = ?
      """;

  private final String ASSIGN = """
      INSERT INTO employees_assessments (employee_id, assessment_id)
      VALUES (?, ?)
      """;

  private final String UPDATE = """
      UPDATE assessments
      SET own_assessment = ?,
        expert_assessment = ?,
        manager_assessment = ?,
        final_assessment = ?,
        date_of_assessment = ?,
        is_active = ?
      WHERE id = ?
      """;

  private final String CREATE = """
      INSERT INTO assessments (own_assessment, expert_assessment, manager_assessment, final_assessment, date_of_assessment, is_active)
      VALUES (?, ?, ?, ?, ?, ?)
      """;

  private final String DELETE = """
      DELETE FROM assessments
      WHERE id = ?
      """;



  @Override
  public Optional<EmployeeAssessment> findById(Integer id) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(FIND_BY_ID);
      statement.setInt(1, id);
      try(ResultSet resultSet = statement.executeQuery()) {
        return Optional.ofNullable(AssessmentRowMapper.mapRow(resultSet));
      }
    } catch (SQLException e) {
      throw new ResourceMappingException("Error while finding assessment by id.");
    }
  }

  @Override
  public Optional<EmployeeAssessment> findByOwnAssessmentId(Integer id) {
    return Optional.empty();
  }

  @Override
  public List<EmployeeAssessment> findAllByEmployeeId(Integer employeeId) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_EMPLOYEE_ID);
      statement.setInt(1, employeeId);
      try(ResultSet resultSet = statement.executeQuery()) {
        return AssessmentRowMapper.mapRows(resultSet);
      }
    } catch (SQLException e) {
      throw new ResourceMappingException("Error while finding all by employee id.");
    }
  }

  @Override
  public void assignToEmployeeById(Integer employeeId, Integer assessmentId) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(ASSIGN);
      statement.setInt(1, employeeId);
      statement.setInt(2, assessmentId);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new ResourceMappingException("Error while assigning to employee.");
    }
  }

  @Override
  public void update(EmployeeAssessment assessment) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(UPDATE);
      statement.setInt(1, assessment.getOwnAssessment());
      statement.setInt(2, assessment.getExpertAssessment());
      statement.setInt(3, assessment.getManagerAssessment());
      statement.setInt(4, assessment.getFinalAssessment());

      if(assessment.getDateOfAssessment() == null) {
        statement.setNull(5, Types.TIMESTAMP);
      } else {
        statement.setTimestamp(5, Timestamp.valueOf(assessment.getDateOfAssessment()));
      }
      statement.setBoolean(6, assessment.isActive());
      statement.setInt(7, assessment.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new ResourceMappingException("Error while updating to employee.");
    }
  }

  @Override
  public void create(EmployeeAssessment assessment) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(CREATE, PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setInt(1, assessment.getOwnAssessment());
      statement.setInt(2, assessment.getExpertAssessment());
      statement.setInt(3, assessment.getManagerAssessment());
      statement.setInt(4, assessment.getFinalAssessment());

      if(assessment.getDateOfAssessment() == null) {
        statement.setNull(5, Types.TIMESTAMP);
      } else {
        statement.setTimestamp(5, Timestamp.valueOf(assessment.getDateOfAssessment()));
      }
      statement.setBoolean(6, assessment.isActive());
      statement.executeUpdate();
      try(ResultSet resultSet = statement.getGeneratedKeys()) {
        resultSet.next();
        assessment.setId(resultSet.getInt(1));
      }
    } catch (SQLException e) {
      throw new ResourceMappingException("Error while creating to employee.");
    }
  }

  @Override
  public void delete(Integer id) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(DELETE);
      statement.setInt(1, id);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new ResourceMappingException("Error while deleting to employee.");
    }
  }

  @Override
  public void updateOwnAssessment(OwnAssessmentDto assessment) {

  }

  @Override
  public void updateManagerAssessment(ManagerAssessmentDto assessment) {

  }

  @Override
  public void updateExpertAssessment(ExpertAssessmentDto assessment) {

  }


}
