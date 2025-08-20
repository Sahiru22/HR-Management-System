package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateInsuranceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateInsuranceResponse;
import com.example.zerocode.employeeregistration.service.controller.response.InsuranceResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.InsuranceNotFoundException;
import java.util.List;

public interface InsuranceService {

  CreateInsuranceResponse addInsurance(CreateInsuranceRequest request, Long employeeId)
      throws EmployeeNotFoundException;

  List<InsuranceResponse> getInsurance(Long employeeId) throws EmployeeNotFoundException;

  CreateInsuranceResponse updateInsurance(CreateInsuranceRequest request, Long employeeId,
      Long insuranceId) throws EmployeeNotFoundException, InsuranceNotFoundException;

  CreateInsuranceResponse deleteInsurance(Long employeeId, Long insuranceId)
      throws EmployeeNotFoundException, InsuranceNotFoundException;
}
