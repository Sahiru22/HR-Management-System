package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateDependentDetailRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateDependentDetailResponse;
import com.example.zerocode.employeeregistration.service.controller.response.DependentDetailResponse;
import com.example.zerocode.employeeregistration.service.exception.DependentDetailsNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import java.util.List;

public interface DependentDetailService {

  CreateDependentDetailResponse addDependentDetails(CreateDependentDetailRequest request,
      Long employeeId) throws EmployeeNotFoundException;

  List<DependentDetailResponse> getDependentDetails(Long employeeId)
      throws EmployeeNotFoundException;

  CreateDependentDetailResponse updateDependentDetails(CreateDependentDetailRequest request,
      Long employeeId, Long dependentDetailsId)
      throws EmployeeNotFoundException, DependentDetailsNotFoundException;

  CreateDependentDetailResponse deleteDependent(Long employeeId, Long dependentDetailsId)
      throws EmployeeNotFoundException, DependentDetailsNotFoundException;
}
