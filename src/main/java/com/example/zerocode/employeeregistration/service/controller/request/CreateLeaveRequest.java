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
public class CreateLeaveRequest {

  @NotBlank
  @Size(max = 255)
  private String leaveType;

  @NotNull
  private Integer leaveBalance;

  @NotNull
  private String leaveDate;
}
