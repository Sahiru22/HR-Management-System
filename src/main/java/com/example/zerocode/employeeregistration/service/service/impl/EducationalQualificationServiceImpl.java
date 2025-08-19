package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEducationalQualificationRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEducationalQualificationResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EducationalQualificationResponse;
import com.example.zerocode.employeeregistration.service.exception.EducationQualificationNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.EducationalQualification;
import com.example.zerocode.employeeregistration.service.repository.EducationalQualificationRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.EducationalQualificationService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class EducationalQualificationServiceImpl implements EducationalQualificationService {

  private final EmployeeRepository employeeRepository;
  private final EducationalQualificationRepository educationalQualificationRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateEducationalQualificationResponse addQualifications(
      CreateEducationalQualificationRequest request, Long employeeId)
      throws EmployeeNotFoundException {

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var educationalQualification = new EducationalQualification();

    modelMapper.map(request, educationalQualification);
    educationalQualification.setEmployee(employee);
    educationalQualificationRepository.save(educationalQualification);

    var response = new CreateEducationalQualificationResponse();
    response.setId(educationalQualification.getId());

    log.info("successfully adding educational qualifications with employee id : {}", employeeId);

    return response;
  }

  @Override
  public List<EducationalQualificationResponse> getQualificationByEmployeeId(Long employeeId)
      throws EmployeeNotFoundException {
    log.info("getting qualification by employee id : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    List<EducationalQualification> educationalQualifications = educationalQualificationRepository.findByEmployee(
        employee);

    return educationalQualifications.stream()
        .map(educationalQualification -> EducationalQualificationResponse.builder()
            .id(educationalQualification.getId())
            .degree(educationalQualification.getDegree())
            .diploma(educationalQualification.getDiploma())
            .institutionName(educationalQualification.getInstitutionName())
            .fieldOfStudy(educationalQualification.getFieldOfStudy())
            .build())
        .toList();
  }

  @Override
  public CreateEducationalQualificationResponse updateQualificationById(
      CreateEducationalQualificationRequest request, Long employeeId,
      Long educationalQualificationId)
      throws EmployeeNotFoundException, EducationQualificationNotFoundException {
    log.info("updating qualification by employee id : {}", employeeId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var educationalQualification = educationalQualificationRepository.findById(
            educationalQualificationId)
        .orElseThrow(() -> new EducationQualificationNotFoundException(
            "Not found qualification with id:" + educationalQualificationId));

    modelMapper.map(request, educationalQualification);
    educationalQualificationRepository.save(educationalQualification);

    var response = new CreateEducationalQualificationResponse();
    response.setId(educationalQualification.getId());

    return response;
  }

  @Override
  public CreateEducationalQualificationResponse deleteQualifications(
      Long educationalQualificationId, Long employeeId)
      throws EmployeeNotFoundException, EducationQualificationNotFoundException {
    log.info("deleting qualification by employee id : {}", employeeId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var educationalQualification = educationalQualificationRepository.findById(
            educationalQualificationId)
        .orElseThrow(() -> new EducationQualificationNotFoundException(
            "Not found qualification with id:" + educationalQualificationId));

    educationalQualificationRepository.delete(educationalQualification);

    var response = new CreateEducationalQualificationResponse();
    response.setId(educationalQualification.getId());

    return response;
  }
}

