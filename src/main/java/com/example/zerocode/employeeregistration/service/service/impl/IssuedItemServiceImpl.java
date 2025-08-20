package com.example.zerocode.employeeregistration.service.service.impl;

import com.example.zerocode.employeeregistration.service.controller.request.CreateIssuedItemRequest;
import com.example.zerocode.employeeregistration.service.controller.response.CreateIssuedItemResponse;
import com.example.zerocode.employeeregistration.service.controller.response.IssuedItemResponse;
import com.example.zerocode.employeeregistration.service.exception.EmployeeNotFoundException;
import com.example.zerocode.employeeregistration.service.exception.IssuedItemNotFoundException;
import com.example.zerocode.employeeregistration.service.model.IssuedItem;
import com.example.zerocode.employeeregistration.service.repository.EmployeeRepository;
import com.example.zerocode.employeeregistration.service.repository.IssuedItemRepository;
import com.example.zerocode.employeeregistration.service.service.IssuedItemService;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class IssuedItemServiceImpl implements IssuedItemService {

  private final IssuedItemRepository issuedItemRepository;
  private final EmployeeRepository employeeRepository;
  private final ModelMapper modelMapper;

  @Override
  public CreateIssuedItemResponse addIssuedItem(CreateIssuedItemRequest request, Long employeeId)
      throws EmployeeNotFoundException {
    log.info("successfully adding issued items with employee id:{}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var issuedItem = new IssuedItem();
    modelMapper.map(request, issuedItem);
    issuedItem.setEmployee(employee);
    issuedItemRepository.save(issuedItem);

    var response = new CreateIssuedItemResponse();
    response.setId(issuedItem.getId());

    return response;
  }

  @Override
  public List<IssuedItemResponse> getIssuedItem(Long employeeId) throws EmployeeNotFoundException {
    log.info("getting issuedItems with employeeId : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    List<IssuedItem> issuedItems = issuedItemRepository.findByEmployee(employee);

    return issuedItems.stream()
        .map(issuedItem -> IssuedItemResponse.builder()
            .id(issuedItem.getId())
            .itemName(issuedItem.getItemName())
            .issuedDate(issuedItem.getIssuedDate())
            .returnDate(issuedItem.getReturnDate())
            .build())
        .toList();
  }

  @Override
  public CreateIssuedItemResponse updateIssuedItem(CreateIssuedItemRequest request, Long employeeId,
      Long issuedItemId) throws EmployeeNotFoundException, IssuedItemNotFoundException {
    log.info("updating issuedItems with employeeId : {}", employeeId);

    var employee = employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var issuedItem = issuedItemRepository.findById(issuedItemId)
        .orElseThrow(
            () -> new IssuedItemNotFoundException("Not found issued item with id:" + issuedItemId));

    modelMapper.map(request, issuedItem);
    issuedItem.setEmployee(employee);
    issuedItemRepository.save(issuedItem);

    var response = new CreateIssuedItemResponse();
    response.setId(issuedItem.getId());

    return response;
  }

  @Override
  public CreateIssuedItemResponse deleteIssuedItem(Long employeeId, Long issuedItemId)
      throws EmployeeNotFoundException, IssuedItemNotFoundException {
    log.info("deleting issuedItems with employeeId : {}", employeeId);

    employeeRepository.findById(employeeId)
        .orElseThrow(
            () -> new EmployeeNotFoundException("Not found employee with id:" + employeeId));

    var issuedItem = issuedItemRepository.findById(issuedItemId)
        .orElseThrow(
            () -> new IssuedItemNotFoundException("Not found issued item with id:" + issuedItemId));

    issuedItemRepository.delete(issuedItem);

    var response = new CreateIssuedItemResponse();
    response.setId(issuedItem.getId());

    return response;
  }
}
