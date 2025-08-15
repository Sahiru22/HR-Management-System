//package com.example.zerocode.employeeregistration.service.model;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import jakarta.validation.constraints.Digits;
//import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.NotNull;
//import jakarta.validation.constraints.Size;
//import lombok.Data;
//
//@Data
//@Entity
//@Table(name = "bonuses")
//public class Bonus {
//
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
//  private Long id;
//
//  @NotBlank
//  @Size(max = 255)
//  private String bonusType;
//
//  @NotNull
//  @Digits(fraction = 2, integer = 8)
//  private Double bonusAmount;
//
//  @NotNull
//  private String bonusDate;
//
//  @ManyToOne
//  @NotNull
//  private Employee employee;
//
//}
