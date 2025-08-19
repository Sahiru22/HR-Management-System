package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateDepartmentRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateDepartmentResponse;
import com.example.zerocode.employeeregistration.service.controller.response.DepartmentResponse;
import com.example.zerocode.employeeregistration.service.exception.DepartmentNotFoundException;
import java.util.List;

public interface DepartmentService {

  CreateDepartmentResponse add(CreateDepartmentRequest request);

  List<DepartmentResponse> getAll();

  DepartmentResponse getById(Long departmentId) throws DepartmentNotFoundException;

  CreateDepartmentResponse deleteById(Long departmentId) throws DepartmentNotFoundException;

  CreateDepartmentResponse updateById(Long departmentId, CreateDepartmentRequest request)
      throws DepartmentNotFoundException;
}
