package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateAllowanceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.AllowanceResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateAllowanceResponse;
import com.example.zerocode.employeeregistration.service.exception.AllowanceNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Allowance;
import com.example.zerocode.employeeregistration.service.repository.AllowanceRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.AllowanceService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class AllowanceServiceImpl implements AllowanceService {

  private final AllowanceRepository allowanceRepository;
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateAllowanceResponse addAllowance(CreateAllowanceRequest request, Long employeeId)
      throws EmployeeNotFoundException {
    log.info("successfully adding allowance by employee id:{}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var allowance = new Allowance();
    modelMapper.map(request, allowance);
    allowance.setEmployee(employee);

    allowanceRepository.save(allowance);

    CreateAllowanceResponse response = new CreateAllowanceResponse();
    response.setId(allowance.getId());

    return response;

  }

  @Override
  public List<AllowanceResponse> getAllowance(Long employeeId) throws EmployeeNotFoundException {
    log.info("getting allowance with employee Id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    List<Allowance> allowances = allowanceRepository.findByEmployee(employee);

    return allowances.stream()
        .map(allowance -> AllowanceResponse.builder()
            .id(allowance.getId())
            .allowanceType(allowance.getAllowanceType())
            .allowanceFee(allowance.getAllowanceFee())
            .allowanceDate(allowance.getAllowanceDate())
            .build())
        .toList();
  }

  @Override
  public List<AllowanceResponse> getAllowanceById(Long employeeId, Long allowanceId)
      throws EmployeeNotFoundException, AllowanceNotFoundException {
    log.info("Getting allowance with employee Id: {} and allowance Id: {}", employeeId,
        allowanceId);

    employeeRepository.findById(employeeId)
        .orElseThrow(() -> new EmployeeNotFoundException(
            "Not found employee with id: " + employeeId));

    var allowance = allowanceRepository.findById(allowanceId)
        .orElseThrow(
            () -> new AllowanceNotFoundException("Not found allowance with id:" + allowanceId));

    return List.of(AllowanceResponse.builder()
        .id(allowance.getId())
        .allowanceType(allowance.getAllowanceType())
        .allowanceFee(allowance.getAllowanceFee())
        .allowanceDate(allowance.getAllowanceDate())
        .build());
  }

  @Override
  public CreateAllowanceResponse updateAllowance(CreateAllowanceRequest request, Long employeeId,
      Long allowanceId) throws EmployeeNotFoundException, AllowanceNotFoundException {
    log.info("update allowance with employee id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var allowance = allowanceRepository.findById(allowanceId)
        .orElseThrow(
            () -> new AllowanceNotFoundException("Not found allowance with id:" + allowanceId));

    modelMapper.map(request, allowance);
    allowance.setEmployee(employee);

    allowanceRepository.save(allowance);

    CreateAllowanceResponse response = new CreateAllowanceResponse();
    response.setId(allowance.getId());

    return response;
  }

  @Override
  public CreateAllowanceResponse deleteAllowance(Long employeeId, Long allowanceId)
      throws EmployeeNotFoundException, AllowanceNotFoundException {
    log.info("delete allowance by employee id : {}", employeeId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var allowance = allowanceRepository.findById(allowanceId)
        .orElseThrow(
            () -> new AllowanceNotFoundException("Not found allowance with id:" + allowanceId));

    allowanceRepository.delete(allowance);

    CreateAllowanceResponse response = new CreateAllowanceResponse();
    response.setId(allowance.getId());

    return response;
  }
}
