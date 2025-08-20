package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

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
  @ManyToOne(optional = false)
  @JoinColumn(name = "department_id")
  private Department department;

  @OneToMany(mappedBy = "employee")
  private List<WorkHistory> workHistories;

  @OneToMany(mappedBy = "employee")
  private List<IssuedItem> issuedItems;

  @OneToMany(mappedBy = "employee")
  private List<EmployeeDesignation> employeeDesignations;

  @OneToMany(mappedBy = "employee")
  private List<Leave> leaves;

  @OneToOne(mappedBy = "employee")
  private Salary salary;

  @OneToMany(mappedBy = "employee")
  private List<Insurance> insurances;
}
