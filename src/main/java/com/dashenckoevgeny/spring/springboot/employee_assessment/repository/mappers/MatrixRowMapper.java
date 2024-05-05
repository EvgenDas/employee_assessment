package com.dashenckoevgeny.spring.springboot.employee_assessment.repository.mappers;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.EmployeeAssessment;
import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Matrix;
import java.sql.ResultSet;
import java.sql.Timestamp;
import lombok.SneakyThrows;

public class MatrixRowMapper {

  @SneakyThrows
  public static Matrix mapRow(ResultSet resultSet) {
    if(resultSet.next()) {
      Matrix matrix = new Matrix();
      matrix.setId(resultSet.getInt("matrix_id"));
      matrix.setWho(resultSet.getString("who"));
      matrix.setDataBase(resultSet.getInt("data_base"));
      matrix.setBuildTools(resultSet.getInt("build_tools"));
      matrix.setTesting(resultSet.getInt("testing"));
      matrix.setVcs(resultSet.getInt("vcs"));
      matrix.setKubernetes(resultSet.getInt("kubernetes"));
      matrix.setDocker(resultSet.getInt("docker"));
      matrix.setJavaCore(resultSet.getInt("javaCore"));
      matrix.setJdbc(resultSet.getInt("jdbc"));
      matrix.setJvm(resultSet.getInt("jvm"));
      matrix.setSecurity(resultSet.getInt("security"));
      matrix.setSpring(resultSet.getInt("spring"));
      matrix.setKafka(resultSet.getInt("kafka"));
      matrix.setOrm(resultSet.getInt("orm"));
      matrix.setCiCd(resultSet.getInt("ciCd"));
      matrix.setHelm(resultSet.getInt("helm"));
      matrix.setMicroservice(resultSet.getInt("microservice"));

      return matrix;
    }
    return null;
  }

}
