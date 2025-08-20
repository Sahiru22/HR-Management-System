package com.example.zerocode.employeeregistration.service.service;

import com.example.zerocode.employeeregistration.service.controller.request.CreateIssuedItemRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateIssuedItemResponse;
import com.example.zerocode.employeeregistration.service.controller.response.IssuedItemResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.IssuedItemNotFoundException;
import java.util.List;

public interface IssuedItemService {

  CreateIssuedItemResponse addIssuedItem(CreateIssuedItemRequest request, Long employeeId)
      throws EmployeeNotFoundException;

  List<IssuedItemResponse> getIssuedItem(Long employeeId) throws EmployeeNotFoundException;

  CreateIssuedItemResponse updateIssuedItem(CreateIssuedItemRequest request, Long employeeId,
      Long issuedItemId) throws EmployeeNotFoundException, IssuedItemNotFoundException;

  CreateIssuedItemResponse deleteIssuedItem(Long employeeId, Long issuedItemId)
      throws EmployeeNotFoundException, IssuedItemNotFoundException;
}
