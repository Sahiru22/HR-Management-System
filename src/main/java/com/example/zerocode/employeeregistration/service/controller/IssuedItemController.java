package com.example.zerocode.employeeregistration.service.controller;

import com.example.zerocode.employeeregistration.service.controller.request.CreateIssuedItemRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateIssuedItemResponse;
import com.example.zerocode.employeeregistration.service.controller.response.IssuedItemResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.IssuedItemNotFoundException;
import com.example.zerocode.employeeregistration.service.service.IssuedItemService;
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
public class IssuedItemController {

  private final IssuedItemService issuedItemService;

  @PostMapping(value = "/employees/{employee-id}/issued-items", headers = "version=v1")
  public CreateIssuedItemResponse addIssuedItems(
      @Valid @RequestBody CreateIssuedItemRequest request,
      @PathVariable("employee-id") Long employeeId) throws EmployeeNotFoundException {
    return issuedItemService.addIssuedItem(request, employeeId);
  }

  @GetMapping(value = "/employees/{employee-id}/issued-items", headers = "version=v1")
  public List<IssuedItemResponse> getIssuedItems(@PathVariable("employee-id") Long employeeId)
      throws EmployeeNotFoundException {
    return issuedItemService.getIssuedItem(employeeId);
  }

  @PutMapping(value = "/employees/{employee-id}/issued-items/{issued-item-id}", headers = "version=v1")
  public CreateIssuedItemResponse updateIssuedItem(
      @Valid @RequestBody CreateIssuedItemRequest request,
      @PathVariable("employee-id") Long employeeId,
      @PathVariable("issued-item-id") Long issuedItemId)
      throws EmployeeNotFoundException, IssuedItemNotFoundException {
    return issuedItemService.updateIssuedItem(request, employeeId, issuedItemId);
  }

  @DeleteMapping(value = "/employees/{employee-id}/issued-items/{issued-item-id}", headers = "version=v1")
  public CreateIssuedItemResponse deleteIssuedItems(@PathVariable("employee-id") Long employeeId,
      @PathVariable("issued-item-id") Long issuedItemId)
      throws EmployeeNotFoundException, IssuedItemNotFoundException {
    return issuedItemService.deleteIssuedItem(employeeId, issuedItemId);
  }
}
