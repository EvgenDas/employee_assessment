package com.dashenckoevgeny.spring.springboot.employee_assessment.repository.impl;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Matrix;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.DataSourceConfig;
import com.dashenckoevgeny.spring.springboot.employee_assessment.repository.MatrixRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MatrixRepositoryImpl implements MatrixRepository {

  private final DataSourceConfig dataSourceConfig;


  @Override
  public Optional<Matrix> findById(Integer id) {
    return Optional.empty();
  }

  @Override
  public List<Matrix> findAllByAssessmentId(Integer id) {
    return null;
  }

  @Override
  public void assignToAssessmentById(Integer assessmentId, Integer matrixId) {

  }

  @Override
  public void update(Matrix assessment) {

  }

  @Override
  public void create(Matrix assessment) {

  }

  @Override
  public void delete(Integer id) {

  }
}
