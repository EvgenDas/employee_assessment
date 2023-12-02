package com.dashenckoevgeny.spring.springboot.employee_assessment.repository;

import java.sql.Connection;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceUtils;

@Configuration
@RequiredArgsConstructor
public class DataSourceConfig {

  private final DataSource dataSource;

  public Connection getConnection() {
    return DataSourceUtils.getConnection(dataSource);
  }
}
