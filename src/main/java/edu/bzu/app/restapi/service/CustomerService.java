package edu.bzu.app.restapi.service;

import edu.bzu.app.restapi.dto.CustomerDto;

import java.util.List;
//just a service interface to declare what methods am I going to provide
public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(long id);

    CustomerDto updateCustomer(CustomerDto customerDto, long id);

    void deleteCustomerById(long id);
}
