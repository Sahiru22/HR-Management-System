package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateAllowanceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.AllowanceResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateAllowanceResponse;
import com.example.zerocode.employeeregistration.service.exception.AllowanceNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import java.util.List;

public interface AllowanceService {

  CreateAllowanceResponse addAllowance(CreateAllowanceRequest request)
      throws EmployeeNotFoundException;

  List<AllowanceResponse> getAllowance(Long employeeId) throws EmployeeNotFoundException;

  List<AllowanceResponse> getAllowanceById(Long allowanceId) throws AllowanceNotFoundException;

  CreateAllowanceResponse updateAllowance(CreateAllowanceRequest request,
      Long allowanceId) throws EmployeeNotFoundException, AllowanceNotFoundException;

  CreateAllowanceResponse deleteAllowance(Long allowanceId)
      throws AllowanceNotFoundException;
}
