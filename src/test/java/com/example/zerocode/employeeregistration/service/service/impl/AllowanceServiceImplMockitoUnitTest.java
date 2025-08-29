package com.example.zerocode.employeeregistration.service.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import com.example.zerocode.employeeregistration.service.controller.request.CreateAllowanceRequest;
import com.example.zerocode.employeeregistration.service.exception.AllowanceNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.model.Allowance;
import com.example.zerocode.employeeregistration.service.model.Employee;
import com.example.zerocode.employeeregistration.service.repository.AllowanceRepository;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

class AllowanceServiceImplMockitoUnitTest {

  @InjectMocks
  private AllowanceServiceImpl allowanceService;

  @Mock
  private AllowanceRepository allowanceRepository;

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private ModelMapper modelMapper;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }


  @Test
  @DisplayName("addAllowance should throw EmployeeNotFoundException when employee not found")
  void testAddAllowance_EmployeeNotFound() {
    var request = new CreateAllowanceRequest();
    request.setEmployeeId(1L);

    Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(EmployeeNotFoundException.class, () -> allowanceService.addAllowance(request));

    Mockito.verify(allowanceRepository, Mockito.never()).save(any(Allowance.class));
  }


  @Test
  @DisplayName("addAllowance should save and return response when employee exists")
  void testAddAllowance_Success() throws Exception {
    var request = new CreateAllowanceRequest();
    request.setEmployeeId(1L);

    var employee = new Employee();
    employee.setId(1L);

    Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
    Mockito.when(allowanceRepository.save(any(Allowance.class)))
        .thenAnswer(invocation -> {
          Allowance a = invocation.getArgument(0);
          a.setId(10L);
          return a;
        });

    var response = allowanceService.addAllowance(request);

    assertNotNull(response);
    assertEquals(10L, response.getId());
    Mockito.verify(allowanceRepository, Mockito.times(1)).save(any(Allowance.class));
  }


  @Test
  @DisplayName("getAllowance should throw EmployeeNotFoundException when employee not found")
  void testGetAllowance_EmployeeNotFound() {
    Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(EmployeeNotFoundException.class, () -> allowanceService.getAllowance(1L));
  }


  @Test
  @DisplayName("getAllowance should return allowance list when employee exists")
  void testGetAllowance_Success() throws Exception {
    var employee = new Employee();
    employee.setId(1L);

    var allowance = new Allowance();
    allowance.setId(5L);
    allowance.setAllowanceType("Transport");
    allowance.setAllowanceFee(BigDecimal.valueOf(1000.0));
    allowance.setAllowanceDate(LocalDate.now());

    Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
    Mockito.when(allowanceRepository.findByEmployee(employee)).thenReturn(List.of(allowance));

    var responses = allowanceService.getAllowance(1L);

    assertEquals(1, responses.size());
    assertEquals("Transport", responses.get(0).getAllowanceType());
  }


  @Test
  @DisplayName("getAllowanceById should throw AllowanceNotFoundException when allowance not found")
  void testGetAllowanceById_NotFound() {
    Mockito.when(allowanceRepository.findById(99L)).thenReturn(Optional.empty());

    assertThrows(AllowanceNotFoundException.class, () -> allowanceService.getAllowanceById(99L));
  }


  @Test
  @DisplayName("getAllowanceById should return allowance response when found")
  void testGetAllowanceById_Success() throws Exception {
    var employee = new Employee();
    employee.setId(1L);
    employee.setFirstName("John");
    employee.setLastName("Doe");

    var allowance = new Allowance();
    allowance.setId(100L);
    allowance.setAllowanceType("Food");
    allowance.setAllowanceFee(BigDecimal.valueOf(500.0));
    allowance.setAllowanceDate(LocalDate.now());
    allowance.setEmployee(employee);

    Mockito.when(allowanceRepository.findById(100L)).thenReturn(Optional.of(allowance));

    var responses = allowanceService.getAllowanceById(100L);

    assertEquals(1, responses.size());
    assertEquals("Food", responses.get(0).getAllowanceType());
    assertEquals("John", responses.get(0).getEmployeeFirstName());
  }


  @Test
  @DisplayName("updateAllowance should throw AllowanceNotFoundException when allowance not found")
  void testUpdateAllowance_AllowanceNotFound() {
    var request = new CreateAllowanceRequest();
    Mockito.when(allowanceRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(AllowanceNotFoundException.class,
        () -> allowanceService.updateAllowance(request, 1L));
  }


  @Test
  @DisplayName("updateAllowance should throw EmployeeNotFoundException when employee not found")
  void testUpdateAllowance_EmployeeNotFound() {
    var request = new CreateAllowanceRequest();
    request.setEmployeeId(1L);

    var allowance = new Allowance();
    allowance.setId(1L);

    Mockito.when(allowanceRepository.findById(1L)).thenReturn(Optional.of(allowance));
    Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(EmployeeNotFoundException.class,
        () -> allowanceService.updateAllowance(request, 1L));
  }


  @Test
  @DisplayName("updateAllowance should update and return response when allowance and employee found")
  void testUpdateAllowance_Success() throws Exception {
    var request = new CreateAllowanceRequest();
    request.setEmployeeId(1L);

    var employee = new Employee();
    employee.setId(1L);

    var allowance = new Allowance();
    allowance.setId(1L);

    Mockito.when(allowanceRepository.findById(1L)).thenReturn(Optional.of(allowance));
    Mockito.when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));
    Mockito.when(allowanceRepository.save(any(Allowance.class))).thenReturn(allowance);

    var response = allowanceService.updateAllowance(request, 1L);

    assertNotNull(response);
    assertEquals(1L, response.getId());
  }


  @Test
  @DisplayName("deleteAllowance should throw AllowanceNotFoundException when not found")
  void testDeleteAllowance_NotFound() {
    Mockito.when(allowanceRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(AllowanceNotFoundException.class, () -> allowanceService.deleteAllowance(1L));
  }


  @Test
  @DisplayName("deleteAllowance should delete and return response when found")
  void testDeleteAllowance_Success() throws Exception {
    var allowance = new Allowance();
    allowance.setId(1L);

    Mockito.when(allowanceRepository.findById(1L)).thenReturn(Optional.of(allowance));

    var response = allowanceService.deleteAllowance(1L);

    assertNotNull(response);
    assertEquals(1L, response.getId());
    Mockito.verify(allowanceRepository, Mockito.times(1)).delete(allowance);
  }
}
