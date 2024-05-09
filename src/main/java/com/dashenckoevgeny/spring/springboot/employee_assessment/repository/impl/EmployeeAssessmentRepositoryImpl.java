package com.dashenckoevgeny.spring.springboot.employee_assessment.repository.impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.exception.ResourceMappingException;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.DataSourceConfig;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.EmployeeAssessmentRepository;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.mappers.AssessmentRowMapper;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.ExpertAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.ManagerAssessmentDto;
import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment.OwnAssessmentDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

//@Repository
@RequiredArgsConstructor
public class EmployeeAssessmentRepositoryImpl implements EmployeeAssessmentRepository {

  private final DataSourceConfig dataSourceConfig;

  private final ObjectMapper objectMapper;

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

  private final String FIND_OWN_ASSESSMENT_BY_ID = """
      select t.own_assessment as assessment
      from assessments t
      where t.id = ?
      """;

  private final String FIND_MANAGER_ASSESSMENT_BY_ID = """
      select t.manager_assessment as assessment
      from assessments t
      where t.id = ?
      """;

  private final String FIND_EXPERT_ASSESSMENT_BY_ID = """
      select t.expert_assessment as assessment
      from assessments t
      where t.id = ?
      """;

  private final String FIND_FINAL_ASSESSMENT_BY_ID = """
      select t.final_assessment as assessment
      from assessments t
      where t.id = ?
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
      try (ResultSet resultSet = statement.executeQuery()) {
        return Optional.ofNullable(AssessmentRowMapper.mapRow(resultSet));
      }
    } catch (SQLException e) {
      throw new ResourceMappingException("Error while finding assessment by id.");
    }
  }

  @SneakyThrows
  @Override
  public Optional<String> findOwnAssessmentById(Integer id) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(FIND_OWN_ASSESSMENT_BY_ID);
      statement.setInt(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        String assessment = null;
        if (resultSet.next()) {
          assessment = resultSet.getString("assessment");
        }
        return Optional.ofNullable(assessment);
      }
    } catch (SQLException e) {
      throw new ResourceMappingException("Error while finding assessment by id.");
    }
  }

  @SneakyThrows
  @Override
  public Optional<String> findManagerAssessmentById(Integer id) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(FIND_MANAGER_ASSESSMENT_BY_ID);
      statement.setInt(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        String assessment = null;
        if (resultSet.next()) {
          assessment = resultSet.getString("assessment");
        }
        return Optional.ofNullable(assessment);
      }
    } catch (SQLException e) {
      throw new ResourceMappingException("Error while finding assessment by id.");
    }
  }

  @SneakyThrows
  @Override
  public Optional<String> findExpertAssessmentById(Integer id) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(FIND_EXPERT_ASSESSMENT_BY_ID);
      statement.setInt(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        String assessment = null;
        if (resultSet.next()) {
          assessment = resultSet.getString("assessment");
        }
        return Optional.ofNullable(assessment);
      }
    } catch (SQLException e) {
      throw new ResourceMappingException("Error while finding assessment by id.");
    }
  }

  @SneakyThrows
  @Override
  public Optional<String> findFinalAssessmentById(Integer id) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(FIND_FINAL_ASSESSMENT_BY_ID);
      statement.setInt(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        String assessment = null;
        if (resultSet.next()) {
          assessment = resultSet.getString("assessment");
        }
        return Optional.ofNullable(assessment);
      }
    } catch (SQLException e) {
      throw new ResourceMappingException("Error while finding assessment by id.");
    }
  }

  @Override
  public List<EmployeeAssessment> findAllByEmployeeId(Integer employeeId) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(FIND_ALL_BY_EMPLOYEE_ID);
      statement.setInt(1, employeeId);
      try (ResultSet resultSet = statement.executeQuery()) {
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
      statement.setString(1, assessment.getOwnAssessment());
      statement.setString(2, assessment.getExpertAssessment());
      statement.setObject(3, assessment.getManagerAssessment());
      statement.setString(4, assessment.getFinalAssessment());

      if (assessment.getDateOfAssessment() == null) {
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
      PreparedStatement statement = connection.prepareStatement(CREATE,
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, assessment.getOwnAssessment());
      statement.setString(2, assessment.getExpertAssessment());
      statement.setObject(3, assessment.getManagerAssessment());
      statement.setString(4, assessment.getFinalAssessment());

      if (assessment.getDateOfAssessment() == null) {
        statement.setNull(5, Types.TIMESTAMP);
      } else {
        statement.setTimestamp(5, Timestamp.valueOf(assessment.getDateOfAssessment()));
      }
      statement.setBoolean(6, assessment.isActive());
      statement.executeUpdate();
      try (ResultSet resultSet = statement.getGeneratedKeys()) {
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
