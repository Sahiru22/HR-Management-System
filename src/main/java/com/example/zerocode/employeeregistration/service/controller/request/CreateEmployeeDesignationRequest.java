package com.example.zerocode.employeeregistration.service.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeDesignationRequest {

  @NotBlank
  @Size(max = 255)
  private String jobPosition;

  @NotNull
  private String startDate;

  @NotNull
  private String endDate;

  @NotNull
  private Long departmentId;
}
