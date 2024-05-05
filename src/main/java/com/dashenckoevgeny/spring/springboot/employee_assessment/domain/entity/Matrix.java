package com.dashenckoevgeny.spring.springboot.employee_assessment.domain.entity;

import java.io.Serializable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Matrix implements Serializable {

  private int id;

  private String who;

  private int dataBase;

  private int buildTools;

  private int testing;

  private int vcs;

  private int kubernetes;

  private int docker;

  private int javaCore;

  private int jdbc;

  private int jvm;

  private int security;

  private int spring;

  private int kafka;

  private int orm;

  private int ciCd;

  private int helm;

  private int microservice;
}
