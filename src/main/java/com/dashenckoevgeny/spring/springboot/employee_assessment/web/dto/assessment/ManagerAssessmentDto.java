package com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.assessment;

import com.dashenckoevgeny.spring.springboot.employee_assessment.web.dto.validation.OnUpdate;
import jakarta.validation.constraints.NotNull;
import java.util.Map;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

@Data
public class ManagerAssessmentDto {

  @NotNull(message = "Id must be not null", groups = OnUpdate.class)
  private Integer id;

  private String managerAssessment;
}
