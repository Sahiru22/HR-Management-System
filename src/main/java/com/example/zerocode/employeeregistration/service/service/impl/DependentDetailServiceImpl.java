package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateDependentDetailRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateDependentDetailResponse;
import com.example.zerocode.employeeregistration.service.controller.response.DependentDetailResponse;
import com.example.zerocode.employeeregistration.service.exception.DependentDetailsNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.DependentDetail;
import com.example.zerocode.employeeregistration.service.repository.DependentDetailRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.DependentDetailService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DependentDetailServiceImpl implements DependentDetailService {

  private final DependentDetailRepository dependentDetailRepository;
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateDependentDetailResponse addDependentDetails(CreateDependentDetailRequest request,
      Long employeeId) throws EmployeeNotFoundException {
    log.info("successfully adding dependent details by employee id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var dependentDetail = new DependentDetail();
    modelMapper.map(request, dependentDetail);
    dependentDetail.setEmployee(employee);
    dependentDetailRepository.save(dependentDetail);

    var response = new CreateDependentDetailResponse();
    response.setId(dependentDetail.getId());

    return response;

  }

  @Override
  public List<DependentDetailResponse> getDependentDetails(Long employeeId)
      throws EmployeeNotFoundException {
    log.info("getting dependent details by employee id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    List<DependentDetail> dependentDetails = dependentDetailRepository.findByEmployee(employee);

    return dependentDetails.stream()
        .map(dependentDetail -> DependentDetailResponse.builder()
            .id(dependentDetail.getId())
            .name(dependentDetail.getName())
            .relationship(dependentDetail.getRelationship())
            .contactNumber(dependentDetail.getContactNumber())
            .email(dependentDetail.getEmail())
            .build())
        .toList();

  }

  @Override
  public CreateDependentDetailResponse updateDependentDetails(CreateDependentDetailRequest request,
      Long employeeId, Long dependentDetailsId)
      throws EmployeeNotFoundException, DependentDetailsNotFoundException {
    log.info("update by employee id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var dependentDetail = dependentDetailRepository.findById(dependentDetailsId)
        .orElseThrow(() -> new DependentDetailsNotFoundException(
            "Not found dependent details with id:" + dependentDetailsId));

    modelMapper.map(request, dependentDetail);
    dependentDetail.setEmployee(employee);
    dependentDetailRepository.save(dependentDetail);

    var response = new CreateDependentDetailResponse();
    response.setId(dependentDetail.getId());

    return response;
  }

  @Override
  public CreateDependentDetailResponse deleteDependent(Long employeeId, Long dependentDetailsId)
      throws EmployeeNotFoundException, DependentDetailsNotFoundException {
    log.info("delete by employee id : {}", employeeId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var dependentDetail = dependentDetailRepository.findById(dependentDetailsId)
        .orElseThrow(() -> new DependentDetailsNotFoundException(
            "Not found dependent details with id:" + dependentDetailsId));

    dependentDetailRepository.delete(dependentDetail);

    var response = new CreateDependentDetailResponse();
    response.setId(dependentDetail.getId());

    return response;
  }
}
