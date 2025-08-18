package com.example.zerocode.employeeregistration.service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.zerocode.employeeregistration.service.controller.request.CreateAllowanceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.AllowanceResponse;
import com.example.zerocode.employeeregistration.service.controller.response.CreateAllowanceResponse;
import com.example.zerocode.employeeregistration.service.exception.AllowanceNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.service.AllowanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AllowanceController.class)
public class AllowanceControllerMockitoUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AllowanceService allowanceService;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("Add new allowance for an employee")
  void testAddAllowance() throws Exception {
    CreateAllowanceRequest request = new CreateAllowanceRequest();
    request.setAllowanceType("Transport");
    request.setAllowanceFee(new BigDecimal("500.00"));
    request.setAllowanceDate("2025-08-16");

    CreateAllowanceResponse response = new CreateAllowanceResponse();
    response.setId(1L);

    when(allowanceService.addAllowance(any(CreateAllowanceRequest.class), eq(1L)))
        .thenReturn(response);

    mockMvc.perform(post("/employees/1/allowances")
            .contentType(MediaType.APPLICATION_JSON)
            .header("version", "v1")
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L));
  }

  @Test
  @DisplayName("Add new allowance - Employee not found")
  void testAddAllowance_EmployeeNotFound() throws Exception {
    CreateAllowanceRequest request = new CreateAllowanceRequest();
    request.setAllowanceType("Transport");
    request.setAllowanceFee(new BigDecimal("500.00"));
    request.setAllowanceDate("2025-08-16");

    when(allowanceService.addAllowance(any(CreateAllowanceRequest.class), eq(999L)))
        .thenThrow(new EmployeeNotFoundException("Employee not found"));

    mockMvc.perform(post("/employees/999/allowances")
            .contentType(MediaType.APPLICATION_JSON)
            .header("version", "v1")
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  @DisplayName("Get all allowances for an employee")
  void testGetAllowances() throws Exception {
    AllowanceResponse allowance1 = new AllowanceResponse();
    allowance1.setAllowanceType("Transport");
    allowance1.setAllowanceFee(new BigDecimal("500.00"));
    allowance1.setAllowanceDate("2025-08-16");

    AllowanceResponse allowance2 = new AllowanceResponse();
    allowance2.setAllowanceType("Food");
    allowance2.setAllowanceFee(new BigDecimal("600.00"));
    allowance2.setAllowanceDate("2025-08-17");

    AllowanceResponse allowance3 = new AllowanceResponse();
    allowance3.setAllowanceType("Vacation");
    allowance3.setAllowanceFee(new BigDecimal("700.00"));
    allowance3.setAllowanceDate("2025-08-18");

    List<AllowanceResponse> allowanceList = List.of(allowance1, allowance2, allowance3);

    when(allowanceService.getAllowance(1L)).thenReturn(allowanceList);

    mockMvc.perform(get("/employees/1/allowances")
            .contentType(MediaType.APPLICATION_JSON)
            .header("version", "v1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(3))
        .andExpect(jsonPath("$[0].allowanceType").value("Transport"))
        .andExpect(jsonPath("$[0].allowanceFee").value(500.00))
        .andExpect(jsonPath("$[0].allowanceDate").value("2025-08-16"))
        .andExpect(jsonPath("$[1].allowanceType").value("Food"))
        .andExpect(jsonPath("$[1].allowanceFee").value(600.00))
        .andExpect(jsonPath("$[1].allowanceDate").value("2025-08-17"))
        .andExpect(jsonPath("$[2].allowanceType").value("Vacation"))
        .andExpect(jsonPath("$[2].allowanceFee").value(700.00))
        .andExpect(jsonPath("$[2].allowanceDate").value("2025-08-18"));
  }

  @Test
  @DisplayName("Get all allowances - Employee not found")
  void testGetAllowances_EmployeeNotFound() throws Exception {
    when(allowanceService.getAllowance(999L))
        .thenThrow(new EmployeeNotFoundException("Employee not found"));

    mockMvc.perform(get("/employees/999/allowances")
            .header("version", "v1"))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  @DisplayName("Get allowance by employeeId and allowanceId")
  void testGetAllowanceById_Success() throws Exception {
    AllowanceResponse response = new AllowanceResponse();
    response.setAllowanceType("Transport");
    response.setAllowanceFee(new BigDecimal("500.00"));
    response.setAllowanceDate("2025-08-16");

    when(allowanceService.getAllowanceById(1L, 10L)).thenReturn(List.of(response));

    mockMvc.perform(get("/employees/1/allowances/10")
            .header("version", "v1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].allowanceType").value("Transport"))
        .andExpect(jsonPath("$[0].allowanceFee").value(500.00))
        .andExpect(jsonPath("$[0].allowanceDate").value("2025-08-16"));
  }

  @Test
  @DisplayName("Get allowance by id - Employee not found")
  void testGetAllowanceById_EmployeeNotFound() throws Exception {
    when(allowanceService.getAllowanceById(999L, 10L))
        .thenThrow(new EmployeeNotFoundException("Employee not found"));

    mockMvc.perform(get("/employees/999/allowances/10")
            .header("version", "v1"))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  @DisplayName("Get allowance by id - Allowance not found")
  void testGetAllowanceById_AllowanceNotFound() throws Exception {
    when(allowanceService.getAllowanceById(1L, 999L))
        .thenThrow(new AllowanceNotFoundException("Allowance not found"));

    mockMvc.perform(get("/employees/1/allowances/999")
            .header("version", "v1"))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  @DisplayName("Update allowance for an employee")
  void testUpdateAllowance() throws Exception {
    CreateAllowanceRequest request = new CreateAllowanceRequest();
    request.setAllowanceType("Transport Updated");
    request.setAllowanceFee(new BigDecimal("550.00"));
    request.setAllowanceDate("2025-08-20");

    CreateAllowanceResponse response = new CreateAllowanceResponse();
    response.setId(10L);

    when(allowanceService.updateAllowance(any(CreateAllowanceRequest.class), eq(1L), eq(10L)))
        .thenReturn(response);

    mockMvc.perform(put("/employees/1/allowances/10")
            .contentType(MediaType.APPLICATION_JSON)
            .header("version", "v1")
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(10L));
  }

  @Test
  @DisplayName("Update allowance - Employee not found")
  void testUpdateAllowance_EmployeeNotFound() throws Exception {
    CreateAllowanceRequest request = new CreateAllowanceRequest();
    request.setAllowanceType("Transport Updated");
    request.setAllowanceFee(new BigDecimal("550.00"));
    request.setAllowanceDate("2025-08-20");

    when(allowanceService.updateAllowance(any(CreateAllowanceRequest.class), eq(999L), eq(10L)))
        .thenThrow(new EmployeeNotFoundException("Employee not found"));

    mockMvc.perform(put("/employees/999/allowances/10")
            .contentType(MediaType.APPLICATION_JSON)
            .header("version", "v1")
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  @DisplayName("Update allowance - Allowance not found")
  void testUpdateAllowance_AllowanceNotFound() throws Exception {
    CreateAllowanceRequest request = new CreateAllowanceRequest();
    request.setAllowanceType("Transport Updated");
    request.setAllowanceFee(new BigDecimal("550.00"));
    request.setAllowanceDate("2025-08-20");

    when(allowanceService.updateAllowance(any(CreateAllowanceRequest.class), eq(1L), eq(999L)))
        .thenThrow(new AllowanceNotFoundException("Allowance not found"));

    mockMvc.perform(put("/employees/1/allowances/999")
            .contentType(MediaType.APPLICATION_JSON)
            .header("version", "v1")
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  @DisplayName("Delete allowance for an employee")
  void testDeleteAllowance() throws Exception {
    CreateAllowanceResponse response = new CreateAllowanceResponse();
    response.setId(10L);

    when(allowanceService.deleteAllowance(1L, 10L)).thenReturn(response);

    mockMvc.perform(delete("/employees/1/allowances/10")
            .header("version", "v1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(10L));
  }

  @Test
  @DisplayName("Delete allowance - Employee not found")
  void testDeleteAllowance_EmployeeNotFound() throws Exception {
    when(allowanceService.deleteAllowance(999L, 10L))
        .thenThrow(new EmployeeNotFoundException("Employee not found"));

    mockMvc.perform(delete("/employees/999/allowances/10")
            .header("version", "v1"))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  @DisplayName("Delete allowance - Allowance not found")
  void testDeleteAllowance_AllowanceNotFound() throws Exception {
    when(allowanceService.deleteAllowance(1L, 999L))
        .thenThrow(new AllowanceNotFoundException("Allowance not found"));

    mockMvc.perform(delete("/employees/1/allowances/999")
            .header("version", "v1"))
        .andExpect(status().isUnprocessableEntity());
  }

  @Test
  @DisplayName("Add allowance - Invalid request body")
  void testAddAllowance_InvalidRequestBody() throws Exception {
    CreateAllowanceRequest request = new CreateAllowanceRequest();

    mockMvc.perform(post("/employees/1/allowances")
            .contentType(MediaType.APPLICATION_JSON)
            .header("version", "v1")
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }

  @Test
  @DisplayName("Update allowance - Invalid request body")
  void testUpdateAllowance_InvalidRequestBody() throws Exception {
    CreateAllowanceRequest request = new CreateAllowanceRequest();

    mockMvc.perform(put("/employees/1/allowances/10")
            .contentType(MediaType.APPLICATION_JSON)
            .header("version", "v1")
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }
}