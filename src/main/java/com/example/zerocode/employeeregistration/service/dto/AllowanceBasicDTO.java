package com.example.zerocode.employeeregistration.service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface AllowanceBasicDTO {

  Long getId();

  String getAllowanceType();

  BigDecimal getAllowanceFee();

  LocalDate getAllowanceDate();

  Employee getEmployee();

  interface Employee {

    Long getId();

    String getFirstName();

    String getLastName();
  }
}
