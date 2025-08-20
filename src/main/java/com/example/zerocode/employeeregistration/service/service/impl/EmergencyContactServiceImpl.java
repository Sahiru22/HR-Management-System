package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateEmergencyContactRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateEmergencyContactResponse;
import com.example.zerocode.employeeregistration.service.controller.response.EmergencyContactResponse;
import com.example.zerocode.employeeregistration.service.exception.EmergencyContactNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.EmergencyContact;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.repository.EmergencyContactRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.service.EmergencyContactService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class EmergencyContactServiceImpl implements EmergencyContactService {

  private final EmergencyContactRepository emergencyContactRepository;
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateEmergencyContactResponse addEmergencyContact(CreateEmergencyContactRequest request,
      Long employeeId) throws EmployeeNotFoundException {
    log.info("successfully adding emergency contacts with employee id: {}", employeeId);

    Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var emergencyContact = new EmergencyContact();
    modelMapper.map(request, emergencyContact);
    emergencyContact.setEmployee(employee);
    emergencyContactRepository.save(emergencyContact);

    var response = new CreateEmergencyContactResponse();
    response.setId(emergencyContact.getId());

    return response;
  }

  @Override
  public List<EmergencyContactResponse> getEmergencyContact(Long employeeId)
      throws EmployeeNotFoundException {
    log.info("getting emergency contact with employeeId: {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    List<EmergencyContact> emergencyContacts = emergencyContactRepository.findByEmployee(employee);

    return emergencyContacts.stream()
        .map(emergencyContact -> EmergencyContactResponse.builder()
            .id(emergencyContact.getId())
            .name(emergencyContact.getName())
            .contactNumber(emergencyContact.getContactNumber())
            .relationship(emergencyContact.getRelationship())
            .build())
        .toList();
  }

  @Override
  public CreateEmergencyContactResponse updateEmergencyContact(
      CreateEmergencyContactRequest request, Long employeeId, Long emergencyContactId)
      throws EmployeeNotFoundException, EmergencyContactNotFoundException {
    log.info("updating emergency contact with employeeId : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var emergencyContact = emergencyContactRepository.findById(emergencyContactId)
        .orElseThrow(() -> new EmergencyContactNotFoundException(
            "Not found emergency contacts with id:" + emergencyContactId));

    modelMapper.map(request, emergencyContact);
    emergencyContact.setEmployee(employee);
    emergencyContactRepository.save(emergencyContact);

    var response = new CreateEmergencyContactResponse();
    response.setId(emergencyContact.getId());

    return response;
  }

  @Override
  public CreateEmergencyContactResponse deleteEmergencyContact(Long employeeId,
      Long emergencyContactId) throws EmployeeNotFoundException, EmergencyContactNotFoundException {
    log.info("deleting emergency contact with employeeId : {}", employeeId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var emergencyContact = emergencyContactRepository.findById(emergencyContactId)
        .orElseThrow(() -> new EmergencyContactNotFoundException(
            "Not found emergency contacts with id:" + emergencyContactId));

    emergencyContactRepository.delete(emergencyContact);

    var response = new CreateEmergencyContactResponse();
    response.setId(emergencyContact.getId());

    return response;
  }
}
