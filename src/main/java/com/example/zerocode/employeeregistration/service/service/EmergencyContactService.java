package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmergencyContactRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmergencyContactResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmergencyContactResponse;
import com.example.zerocode.employeeregistration.service.exception.EmergencyContactNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import java.util.List;

public interface EmergencyContactService {

  CreateEmergencyContactResponse addEmergencyContact(CreateEmergencyContactRequest request,
      Long employeeId) throws EmployeeNotFoundException;

  List<EmergencyContactResponse> getEmergencyContact(Long employeeId)
      throws EmployeeNotFoundException;

  CreateEmergencyContactResponse updateEmergencyContact(CreateEmergencyContactRequest request,
      Long employeeId, Long emergencyContactId)
      throws EmployeeNotFoundException, EmergencyContactNotFoundException;

  CreateEmergencyContactResponse deleteEmergencyContact(Long employeeId, Long emergencyContactId)
      throws EmployeeNotFoundException, EmergencyContactNotFoundException;
}
