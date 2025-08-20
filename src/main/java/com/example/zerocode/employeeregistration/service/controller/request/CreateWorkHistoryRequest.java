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
public class CreateWorkHistoryRequest {

  @NotBlank
  @Size(max = 255)
  private String workPlace;

  @NotBlank
  @Size(max = 255)
  private String jobTitle;

  @NotBlank
  @Size(max = 255)
  private String project;

  @NotNull
  private String startDate;

  @NotNull
  private String endDate;
}
