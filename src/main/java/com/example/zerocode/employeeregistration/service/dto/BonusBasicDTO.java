package com.example.zerocode.employeeregistration.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface BonusBasicDTO {

  Long getId();

  String getBonusType();

  BigDecimal getBonusAmount();

  LocalDate getBonusDate();

  Employee getEmployee();

  interface Employee {

    Long getId();

    String getFirstName();

    String getLastName();
  }
}
