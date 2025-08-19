package com.example.zerocode.employeeregistration.service.controller.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDependentDetailRequest {

  @NotBlank
  @Size(max = 60)
  private String relationship;

  @NotBlank
  @Size(max = 255)
  private String name;

  @NotBlank
  @Digits(integer = 10, fraction = 0)
  private String contactNumber;

  @NotBlank
  @Size(max = 255)
  private String address;

  @Email
  @NotBlank
  private String email;

  @Size(max = 255)
  private String memo;
}
