package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEducationalQualificationRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEducationalQualificationResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EducationalQualificationResponse;
import com.example.zerocode.employeeregistration.service.exception.EducationQualificationNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import java.util.List;

public interface EducationalQualificationService {

  CreateEducationalQualificationResponse addQualifications(
      CreateEducationalQualificationRequest request, Long employeeId)
      throws EmployeeNotFoundException;

  List<EducationalQualificationResponse> getQualificationByEmployeeId(Long employeeId)
      throws EmployeeNotFoundException;

  CreateEducationalQualificationResponse updateQualificationById(
      CreateEducationalQualificationRequest request, Long employeeId,
      Long educationalQualificationId)
      throws EmployeeNotFoundException, EducationQualificationNotFoundException;

  CreateEducationalQualificationResponse deleteQualifications(Long educationalQualificationId,
      Long employeeId) throws EmployeeNotFoundException, EducationQualificationNotFoundException;
}
