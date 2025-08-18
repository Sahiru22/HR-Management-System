package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmployeeRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmployeeResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmployeeResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import java.util.List;

public interface EmployeeService {

  CreateEmployeeResponse add(CreateEmployeeRequest request) throws DepartmentNotFoundException;

  List<EmployeeResponse> getAll();

  EmployeeResponse getById(Long employeeId) throws EmployeeNotFoundException;

  CreateEmployeeResponse deleteById(Long employeeId) throws EmployeeNotFoundException;

  CreateEmployeeResponse updateById(Long employeeId, CreateEmployeeRequest request)
      throws EmployeeNotFoundException, DepartmentNotFoundException;

  List<EmployeeResponse> getAllEmployeeByDepartmentId(Long departmentId)
      throws DepartmentNotFoundException;
}
