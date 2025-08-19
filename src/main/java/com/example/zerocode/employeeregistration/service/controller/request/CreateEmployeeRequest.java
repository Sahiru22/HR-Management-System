package com.example.zerocode.employeeregistration.service.controller.request;

import com.example.zerocode.employeeregistration.service.model.Gender;
import com.example.zerocode.employeeregistration.service.model.MaritalStatus;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateEmployeeRequest {

  @NotBlank
  @Size(max = 60)
  private String firstName;

  @NotBlank
  @Size(max = 60)
  private String lastName;

  @NotNull
  private Integer age;

  @NotBlank
  @Size(max = 255)
  private String address;

  @NotNull
  private Gender gender;

  @NotBlank
  @Email
  @Size(max = 60)
  private String email;

  private String bloodGroup;

  @NotNull
  private MaritalStatus maritalStatus;

  @NotNull
  private LocalDate birthDate;

  @NotBlank
  @Digits(integer = 10, fraction = 0)
  private String phoneNumber;

  @NotNull
  private Long departmentId;
}
