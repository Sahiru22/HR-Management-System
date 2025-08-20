package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmployeeDesignationRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmployeeDesignationResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmployeeDesignationResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeDesignationNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import java.util.List;

public interface EmployeeDesignationService {

  CreateEmployeeDesignationResponse addEmployeeDesignation(CreateEmployeeDesignationRequest request,
      Long employeeId) throws EmployeeNotFoundException, DepartmentNotFoundException;

  List<EmployeeDesignationResponse> getEmployeeDesignation(Long employeeId)
      throws EmployeeNotFoundException;

  CreateEmployeeDesignationResponse updateEmployeeDesignation(
      CreateEmployeeDesignationRequest request, Long employeeId, Long employeeDesignationId)
      throws EmployeeNotFoundException, EmployeeDesignationNotFoundException, DepartmentNotFoundException;

  CreateEmployeeDesignationResponse deleteEmployeeDesignation(Long employeeDesignationId,
      Long employeeId) throws EmployeeNotFoundException, EmployeeDesignationNotFoundException;
}
