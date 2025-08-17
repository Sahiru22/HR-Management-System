package com.example.zerocode.employeeregistration.service.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.zerocode.employeeregistration.service.controller.request.CreateAllowanceRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateAllowanceResponse;
import com.example.zerocode.employeeregistration.service.model.Allowance;
import com.example.zerocode.employeeregistration.service.service.AllowanceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

//  @Test
//  @DisplayName("Get all allowances for an employee")
//  void testGetAllowances_JSONPATH() throws Exception {
//
//    Allowance allowance1 = new Allowance();
//    allowance1.setId(101L);
//    allowance1.setAllowanceType("Transport");
//    allowance1.setAllowanceFee(new BigDecimal("500.00"));
//    allowance1.setAllowanceDate("2025-08-16");
//
//    Allowance allowance2 = new Allowance();
//    allowance2.setId(102L);
//    allowance2.setAllowanceType("Food");
//    allowance2.setAllowanceFee(new BigDecimal("600.00"));
//    allowance2.setAllowanceDate("2025-08-17");
//
//    Allowance allowance3 = new Allowance();
//    allowance3.setId(103L);
//    allowance3.setAllowanceType("Vacation");
//    allowance3.setAllowanceFee(new BigDecimal("700.00"));
//    allowance3.setAllowanceDate("2025-08-18");
//
//    var allowanceList = List.of(allowance1, allowance2, allowance3);
//
//    Mockito.when(allowanceService.getAllowance(1L)).thenReturn(allowanceList);
//
//    var resultActions = mockMvc.perform(
//        MockMvcRequestBuilders
//            .get("/employees/{employee-id}/allowances", 1L)
//            .contentType(MediaType.APPLICATION_JSON)
//            .header("version", "v1")
//    );
//
//    resultActions.andExpect(MockMvcResultMatchers.status().isOk());
//    resultActions.andExpect(jsonPath("$.length()").value(3));
//    resultActions.andExpect(jsonPath("$[0].id").value(allowance1.getId()));
//    resultActions.andExpect(jsonPath("$[0].allowanceType").value(allowance1.getAllowanceType()));
//    resultActions.andExpect(jsonPath("$[0].allowanceFee").value(allowance1.getAllowanceFee()));
//    resultActions.andExpect(jsonPath("$[1].id").value(allowance2.getId()));
//    resultActions.andExpect(jsonPath("$[1].allowanceType").value(allowance2.getAllowanceType()));
//    resultActions.andExpect(jsonPath("$[1].allowanceFee").value(allowance2.getAllowanceFee()));
//    resultActions.andExpect(jsonPath("$[2].id").value(allowance3.getId()));
//    resultActions.andExpect(jsonPath("$[2].allowanceType").value(allowance3.getAllowanceType()));
//    resultActions.andExpect(jsonPath("$[2].allowanceFee").value(allowance3.getAllowanceFee()));
//  }
}
