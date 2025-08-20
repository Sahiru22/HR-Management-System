package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateInsuranceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateInsuranceResponse;
import com.example.zerocode.employeeregistration.service.controller.response.InsuranceResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.InsuranceNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Insurance;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.repository.InsuranceRepository;
import com.example.zerocode.employeeregistration.service.service.InsuranceService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class InsuranceServiceImpl implements InsuranceService {

  private final InsuranceRepository insuranceRepository;
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateInsuranceResponse addInsurance(CreateInsuranceRequest request, Long employeeId)
      throws EmployeeNotFoundException {
    log.info("successfully adding insurance with employee id:{}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var insurance = new Insurance();
    modelMapper.map(request, insurance);
    insurance.setEmployee(employee);
    insuranceRepository.save(insurance);

    var response = new CreateInsuranceResponse();
    response.setId(insurance.getId());

    return response;
  }

  @Override
  public List<InsuranceResponse> getInsurance(Long employeeId) throws EmployeeNotFoundException {
    log.info("getting insurance by employee id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    List<Insurance> insurances = insuranceRepository.findByEmployee(employee);

    return insurances.stream()
        .map(insurance -> InsuranceResponse.builder()
            .id(insurance.getId())
            .insuranceType(insurance.getInsuranceType())
            .insuranceFee(insurance.getInsuranceFee())
            .period(insurance.getPeriod())
            .monthlyDeductedAmount(insurance.getMonthlyDeductedAmount())
            .build())
        .toList();

  }

  @Override
  public CreateInsuranceResponse updateInsurance(CreateInsuranceRequest request, Long employeeId,
      Long insuranceId) throws EmployeeNotFoundException, InsuranceNotFoundException {
    log.info("update insurance by employee id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var insurance = insuranceRepository.findById(insuranceId)
        .orElseThrow(
            () -> new InsuranceNotFoundException("Not found insurance with id:" + insuranceId));

    modelMapper.map(request, insurance);
    insurance.setEmployee(employee);
    insuranceRepository.save(insurance);

    var response = new CreateInsuranceResponse();
    response.setId(insurance.getId());

    return response;
  }

  @Override
  public CreateInsuranceResponse deleteInsurance(Long employeeId, Long insuranceId)
      throws EmployeeNotFoundException, InsuranceNotFoundException {
    log.info("delete insurance by employee id : {}", employeeId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var insurance = insuranceRepository.findById(insuranceId)
        .orElseThrow(
            () -> new InsuranceNotFoundException("Not found insurance with id:" + insuranceId));

    insuranceRepository.delete(insurance);

    var response = new CreateInsuranceResponse();
    response.setId(insurance.getId());

    return response;
  }
}
