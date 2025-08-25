package com.example.zerocode.employeeregistration.service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
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
import com.example.zerocode.employeeregistration.service.dto.AllowanceBasicDTO;
import com.example.zerocode.employeeregistration.service.exception.AllowanceNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.repository.AllowanceRepository;
import com.example.zerocode.employeeregistration.service.service.AllowanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.querydsl.core.types.Predicate;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AllowanceController.class)
public class AllowanceControllerMockitoUnitTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private AllowanceService allowanceService;

  @MockBean
  private AllowanceRepository allowanceRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  @DisplayName("Add allowance")
  void testAddAllowance() throws Exception {
    CreateAllowanceRequest request = new CreateAllowanceRequest();
    request.setAllowanceType("Transport");
    request.setAllowanceFee(new BigDecimal("500.00"));
    request.setAllowanceDate(LocalDate.parse("2025-08-16"));
    request.setEmployeeId(1L);

    CreateAllowanceResponse response = new CreateAllowanceResponse();
    response.setId(1L);

    when(allowanceService.addAllowance(any(CreateAllowanceRequest.class)))
        .thenReturn(response);

    mockMvc.perform(post("/allowances")
            .header("version", "v1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L));
  }


  @Test
  @DisplayName("Find allowances with search and date filter")
  void testFindAllowances() throws Exception {
    AllowanceBasicDTO dto1 = mock(AllowanceBasicDTO.class);
    when(dto1.getId()).thenReturn(1L);
    when(dto1.getAllowanceType()).thenReturn("Transport");
    when(dto1.getAllowanceFee()).thenReturn(new BigDecimal("500.00"));
    when(dto1.getAllowanceDate()).thenReturn(LocalDate.parse("2025-08-16"));

    AllowanceBasicDTO dto2 = mock(AllowanceBasicDTO.class);
    when(dto2.getId()).thenReturn(2L);
    when(dto2.getAllowanceType()).thenReturn("Food");
    when(dto2.getAllowanceFee()).thenReturn(new BigDecimal("600.00"));
    when(dto2.getAllowanceDate()).thenReturn(LocalDate.parse("2025-08-17"));

    Page<AllowanceBasicDTO> page = new PageImpl<>(List.of(dto1, dto2));
    when(allowanceRepository.findBy(any(Predicate.class), any())).thenReturn(page);

    mockMvc.perform(get("/allowances")
            .header("version", "v1")
            .param("q", "Food")
            .param("date", "2025-08-20")
            .param("page", "0")
            .param("size", "10"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.content.length()").value(2))
        .andExpect(jsonPath("$.content[0].allowanceType").value("Transport"))
        .andExpect(jsonPath("$.content[0].allowanceFee").value(500.00))
        .andExpect(jsonPath("$.content[1].allowanceType").value("Food"))
        .andExpect(jsonPath("$.content[1].allowanceFee").value(600.00));
  }


  @Test
  @DisplayName("Get all allowances for an employee")
  void testGetAllowances() throws Exception {
    AllowanceResponse allowance1 = new AllowanceResponse();
    allowance1.setAllowanceType("Transport");
    allowance1.setAllowanceFee(new BigDecimal("500.00"));
    allowance1.setAllowanceDate(LocalDate.parse("2025-08-16"));

    AllowanceResponse allowance2 = new AllowanceResponse();
    allowance2.setAllowanceType("Food");
    allowance2.setAllowanceFee(new BigDecimal("600.00"));
    allowance2.setAllowanceDate(LocalDate.parse("2025-08-17"));

    AllowanceResponse allowance3 = new AllowanceResponse();
    allowance3.setAllowanceType("Vacation");
    allowance3.setAllowanceFee(new BigDecimal("700.00"));
    allowance3.setAllowanceDate(LocalDate.parse("2025-08-18"));

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
  @DisplayName("Get allowances - Employee not found")
  void testGetAllowances_EmployeeNotFound() throws Exception {
    when(allowanceService.getAllowance(999L))
        .thenThrow(new EmployeeNotFoundException("Employee not found"));

    mockMvc.perform(get("/employees/999/allowances")
            .header("version", "v1"))
        .andExpect(status().isUnprocessableEntity());
  }


  @Test
  @DisplayName("Get allowance by ID with employee info")
  void testGetAllowanceById() throws Exception {
    AllowanceResponse response = new AllowanceResponse();
    response.setAllowanceType("Transport");
    response.setAllowanceFee(new BigDecimal("500.00"));
    response.setAllowanceDate(LocalDate.parse("2025-08-16"));
    response.setEmployeeId(1L);
    response.setEmployeeFirstName("Joy");
    response.setEmployeeLastName("Root");

    when(allowanceService.getAllowanceById(10L)).thenReturn(List.of(response));

    mockMvc.perform(get("/allowances/10")
            .header("version", "v1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].allowanceType").value("Transport"))
        .andExpect(jsonPath("$[0].allowanceFee").value(500.00))
        .andExpect(jsonPath("$[0].allowanceDate").value("2025-08-16"))
        .andExpect(jsonPath("$[0].employeeId").value(1))
        .andExpect(jsonPath("$[0].employeeFirstName").value("Joy"))
        .andExpect(jsonPath("$[0].employeeLastName").value("Root"));
  }


  @Test
  @DisplayName("Get allowance by ID - Allowance not found")
  void testGetAllowanceById_AllowanceNotFound() throws Exception {
    when(allowanceService.getAllowanceById(999L))
        .thenThrow(new AllowanceNotFoundException("Allowance not found"));

    mockMvc.perform(get("/allowances/999")
            .header("version", "v1"))
        .andExpect(status().isUnprocessableEntity());
  }


  @Test
  @DisplayName("Update allowance")
  void testUpdateAllowance() throws Exception {
    CreateAllowanceRequest request = new CreateAllowanceRequest();
    request.setAllowanceType("Updated Transport");
    request.setAllowanceFee(new BigDecimal("550.00"));
    request.setAllowanceDate(LocalDate.parse("2025-08-20"));
    request.setEmployeeId(1L);

    CreateAllowanceResponse response = new CreateAllowanceResponse();
    response.setId(10L);

    when(allowanceService.updateAllowance(any(CreateAllowanceRequest.class), any(Long.class)))
        .thenReturn(response);

    mockMvc.perform(put("/allowances/10")
            .header("version", "v1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(10L));
  }


  @Test
  @DisplayName("Update allowance - Allowance not found")
  void testUpdateAllowance_AllowanceNotFound() throws Exception {
    CreateAllowanceRequest request = new CreateAllowanceRequest();
    request.setAllowanceType("Transport");
    request.setAllowanceFee(new BigDecimal("500.00"));
    request.setAllowanceDate(LocalDate.parse("2025-08-16"));
    request.setEmployeeId(1L);

    when(allowanceService.updateAllowance(any(CreateAllowanceRequest.class), any(Long.class)))
        .thenThrow(new AllowanceNotFoundException("Allowance not found"));

    mockMvc.perform(put("/allowances/999")
            .header("version", "v1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isUnprocessableEntity());
  }


  @Test
  @DisplayName("Delete allowance")
  void testDeleteAllowance() throws Exception {
    CreateAllowanceResponse response = new CreateAllowanceResponse();
    response.setId(10L);

    when(allowanceService.deleteAllowance(10L)).thenReturn(response);

    mockMvc.perform(delete("/allowances/10")
            .header("version", "v1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(10L));
  }


  @Test
  @DisplayName("Delete allowance - Allowance not found")
  void testDeleteAllowance_AllowanceNotFound() throws Exception {
    when(allowanceService.deleteAllowance(999L))
        .thenThrow(new AllowanceNotFoundException("Allowance not found"));

    mockMvc.perform(delete("/allowances/999")
            .header("version", "v1"))
        .andExpect(status().isUnprocessableEntity());
  }


  @Test
  @DisplayName("Add allowance - Invalid request body")
  void testAddAllowance_InvalidRequestBody() throws Exception {
    CreateAllowanceRequest request = new CreateAllowanceRequest();

    mockMvc.perform(post("/allowances")
            .header("version", "v1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }


  @Test
  @DisplayName("Update allowance - Invalid request body")
  void testUpdateAllowance_InvalidRequestBody() throws Exception {
    CreateAllowanceRequest request = new CreateAllowanceRequest();

    mockMvc.perform(put("/allowances/10")
            .header("version", "v1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }
}