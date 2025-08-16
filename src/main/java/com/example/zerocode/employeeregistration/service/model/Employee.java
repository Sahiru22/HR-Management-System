package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "employees")
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "Employee_name")
  private String name;
  private Integer age;
  private String address;
  private String gender;
  private String email;
  private String bloodGroup;
  private String maritalStatus;
  private String dateOfBirth;

  @ManyToOne
  private Department department;

  @OneToMany(mappedBy = "employee")
  private List<EducationalQualification> educationalQualifications;

  @OneToMany(mappedBy = "employee")
  private List<WorkHistory> workHistories;

  @OneToMany(mappedBy = "employee")
  private List<DependentDetail> dependentDetails;

  @OneToMany(mappedBy = "employee")
  private List<IssuedItem> issuedItems;

  @OneToMany(mappedBy = "employee")
  private List<EmergencyContact> emergencyContacts;

  @OneToMany(mappedBy = "employee")
  private List<EmployeeDesignation> employeeDesignations;

  @OneToMany(mappedBy = "employee")
  private List<Leave> leaves;

//    @OneToMany(mappedBy = "employee")
//    private List<Bonus> bonuses;

  @OneToOne(mappedBy = "employee")
  private Salary salary;

  @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private List<Allowance> allowances;

  @OneToMany(mappedBy = "employee")
  private List<Insurance> insurances;
}
