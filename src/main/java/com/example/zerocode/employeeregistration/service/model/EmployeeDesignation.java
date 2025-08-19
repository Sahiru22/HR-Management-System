package com.example.zerocode.employeeregistration.service.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "employee_designations")
public class EmployeeDesignation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String jobPosition;
    private String startDate;
    private String endDate;

    @ManyToOne
    private Employee employee;

//    @ManyToOne
//    private Department department;
}
