package com.dashenckoevgeny.spring.springboot.employee_assessment.domain.exception;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionBody {

  private String message;
  private Map<String, String> errors;

  public ExceptionBody(String message) {
    this.message = message;
  }
}
