package com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment;

import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ExpertAssessmentDto {

  @NotNull(message = "Id must be not null", groups = OnUpdate.class)
  private Integer id;

  private Integer expertAssessment;

}
