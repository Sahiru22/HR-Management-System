package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmergencyContactRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmergencyContactResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmergencyContactResponse;
import com.example.zerocode.employeeregistration.service.exception.EmergencyContactNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.service.EmergencyContactService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class EmergencyContactController {

  private final EmergencyContactService emergencyContactService;

  @PostMapping(value = "/employees/{employee-id}/emergency-contacts", headers = "version=v1")
  public CreateEmergencyContactResponse addEmergencyContact(
      @Valid @RequestBody CreateEmergencyContactRequest request,
      @PathVariable("employee-id") Long employeeId) throws EmployeeNotFoundException {
    return emergencyContactService.addEmergencyContact(request, employeeId);
  }

  @GetMapping(value = "/employees/{employee-id}/emergency-contacts", headers = "version=v1")
  public List<EmergencyContactResponse> getEmergencyContact(
      @PathVariable("employee-id") Long employeeId) throws EmployeeNotFoundException {
    return emergencyContactService.getEmergencyContact(employeeId);
  }

  @PutMapping(value = "/employees/{employee-id}/emergency-contacts/{emergency-contact-id}", headers = "version=v1")
  public CreateEmergencyContactResponse updateEmergencyContact(
      @Valid @RequestBody CreateEmergencyContactRequest request,
      @PathVariable("employee-id") Long employeeId,
      @PathVariable("emergency-contact-id") Long emergencyContactId)
      throws EmployeeNotFoundException, EmergencyContactNotFoundException {
    return emergencyContactService.updateEmergencyContact(request, employeeId, emergencyContactId);
  }

  @DeleteMapping(value = "/employees/{employee-id}/emergency-contacts/{emergency-contact-id}", headers = "version=v1")
  public CreateEmergencyContactResponse deleteEmergencyContact(
      @PathVariable("employee-id") Long employeeId,
      @PathVariable("emergency-contact-id") Long emergencyContactId)
      throws EmployeeNotFoundException, EmergencyContactNotFoundException {
    return emergencyContactService.deleteEmergencyContact(employeeId, emergencyContactId);
  }
}
