package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

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
    private List <EducationalQualification> educationalQualifications;

    @OneToMany(mappedBy = "employee")
    private List <WorkHistory> workHistories;

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

    @OneToMany(mappedBy = "employee")
    private List<Allowance> allowances;

    @OneToMany(mappedBy = "employee")
    private List<Insurance> insurances;
}
