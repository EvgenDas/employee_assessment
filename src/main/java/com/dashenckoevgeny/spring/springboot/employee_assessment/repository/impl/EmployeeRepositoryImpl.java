package com.dashenckoevgeny.spring.springboot.employee_assessment.repository.impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Employee;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Role;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.exception.ResourceMappingException;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.DataSourceConfig;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.EmployeeRepository;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.mappers.EmployeeRowMappers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

//@Repository
@RequiredArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

  private final DataSourceConfig dataSourceConfig;

  private final String FIND_BY_ID = """
      select e.id as employee_id,
        e.name as name,
        e.surname as surname,
        e.manager_id as manager_id,
        e.expert_id as expert_id,
        e.role as employee_role,
        a.id as assessment_id,
        a.own_assessment as own_assessment,
        a.expert_assessment as expert_assessment,
        a.manager_assessment as manager_assessment,
        a.final_assessment as final_assessment,
        a.date_of_assessment as date_of_assessment,
        a.is_active as is_active,
        e.date_of_next_assessment as employee_date_of_next_assessment,
        e.login as employee_login,
        e.password as password
      from employees e
        LEFT JOIN employees_roles er on e.id = er.employee_id
        LEFT JOIN employees_assessments ea on e.id = ea.employee_id
        LEFT JOIN assessments a on ea.assessment_id = a.id
      where e.id = ?;
      """;

  private final String FIND_BY_LOGIN = """
      select e.id as employee_id,
        e.name as name,
        e.surname as surname,
        e.manager_id as manager_id,
        e.expert_id as expert_id,
        er.role as employee_role,
        a.id as assessment_id,
        a.own_assessment as own_assessment,
        a.expert_assessment as expert_assessment,
        a.manager_assessment as manager_assessment,
        a.final_assessment as final_assessment,
        a.date_of_assessment as date_of_assessment,
        a.is_active as is_active,
        e.date_of_next_assessment as employee_date_of_next_assessment,
        e.login as employee_login,
        e.password as password
      from employees e
        LEFT JOIN employees_roles er on e.id = er.employee_id
        LEFT JOIN employees_assessments ea on e.id = ea.employee_id
        LEFT JOIN assessments a on ea.assessment_id = a.id
      where e.login = ?;
      """;

  private final String UPDATE = """
      UPDATE employees
      SET name = ?,
          surname = ?,
          manager_id = ?,
          expert_id = ?,
          login = ?,
          password = ?
      WHERE id = ?
      """;

  private final String CREATE = """
      INSERT INTO employees (name, surname, manager_id, expert_id, login, password)
      VALUES (?, ?, ?, ?, ?, ?)
      """;

  private final String INSERT_EMPLOYEE_ROLE = """
      INSERT INTO employees_roles (employee_id, role)
      VALUES (?, ?)
      """;

  private final String IS_TASK_OWNER = """
      select exists (
        select 1
        from employees_assessments
        where employee_id = ?
        and assessment_id = ?
      )
      """;

  private final String DELETE = """
      DELETE FROM employees
      WHERE id = ?
      """;

  @Override
  public Optional<Employee> findByLogin(String login) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN,
          ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      statement.setString(1, login);
      try (ResultSet resultSet = statement.executeQuery()) {
        return Optional.ofNullable(EmployeeRowMappers.mapRow(resultSet));
      }

    } catch (SQLException e) {
      throw new ResourceMappingException("Exception while finding employee by login");
    }
  }

  @Override
  public Optional<Employee> findById(Integer id) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(FIND_BY_ID,
          ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      statement.setInt(1, id);
      try (ResultSet resultSet = statement.executeQuery()) {
        return Optional.ofNullable(EmployeeRowMappers.mapRow(resultSet));
      }

    } catch (SQLException e) {
      throw new ResourceMappingException("Exception while finding employee by id");
    }
  }

  @Override
  public void update(Employee employee) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(UPDATE);
      statement.setString(1, employee.getName());
      statement.setString(2, employee.getSurname());
      statement.setInt(3, employee.getManagerId());
      statement.setInt(4, employee.getExpertId());
      statement.setString(5, employee.getLogin());
      statement.setString(6, employee.getPassword());
      statement.setInt(7, employee.getId());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new ResourceMappingException("Exception while updating employee");
    }
  }

  @Override
  public void create(Employee employee) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(CREATE,
          PreparedStatement.RETURN_GENERATED_KEYS);
      statement.setString(1, employee.getName());
      statement.setString(2, employee.getSurname());
      statement.setInt(3, employee.getManagerId());
      statement.setInt(4, employee.getExpertId());
      statement.setString(5, employee.getLogin());
      statement.setString(6, employee.getPassword());
      statement.executeUpdate();
      try (ResultSet resultSet = statement.getGeneratedKeys()) {
        resultSet.next();
        employee.setId(resultSet.getInt(1));
      }
    } catch (SQLException e) {
      throw new ResourceMappingException("Exception while creating employee");
    }
  }

  @Override
  public void insertEmployeeRole(Integer employeeId, Role role) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(INSERT_EMPLOYEE_ROLE);
      statement.setInt(1, employeeId);
      statement.setString(2, role.name());
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new ResourceMappingException("Exception while inserting employee role");
    }
  }

  @Override
  public boolean isAssessmentOwner(Integer employeeId, Integer assessmentId) {
    try {
      Connection connection = dataSourceConfig.getConnection();
      PreparedStatement statement = connection.prepareStatement(IS_TASK_OWNER);
      statement.setInt(1, employeeId);
      statement.setInt(2, assessmentId);
      try (ResultSet resultSet = statement.executeQuery()) {
        resultSet.next();
        return resultSet.getBoolean(1);
      }
    } catch (SQLException e) {
      throw new ResourceMappingException(
          "Exception while checking if employee is assessment owner");
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
      throw new ResourceMappingException(
          "Exception while checking if employee is assessment owner");
    }
  }
}
