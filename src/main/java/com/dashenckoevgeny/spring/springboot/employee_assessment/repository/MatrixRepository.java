package com.dashenckoevgeny.spring.springboot.employee_assessment.repository;

import com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity.Matrix;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Param;

public interface MatrixRepository {

  Optional<Matrix> findById(Integer id);

  List<Matrix> findAllByAssessmentId(Integer id);

  void assignToAssessmentById(@Param("assessmentId") Integer assessmentId,
      @Param("matrixId") Integer matrixId);

  void update(Matrix assessment);

  void create(Matrix assessment);

  void delete(Integer id);

}
