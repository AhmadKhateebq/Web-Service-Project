package edu.bzu.app.restapi.service.impl;

import edu.bzu.app.restapi.dto.CustomerDto;
import edu.bzu.app.restapi.entity.Customer;
import edu.bzu.app.restapi.exception.ResourceNotFoundException;
import edu.bzu.app.restapi.repository.CustomerRepository;
import edu.bzu.app.restapi.service.CustomerService;
import edu.bzu.app.restapi.utils.MapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
//implementation of that interface, service to enable component scan
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository repository;
    @Autowired
    MapStruct mapper;

    public CustomerDto createCustomer(CustomerDto dto) {
        //used the mapper twice to, 1-add DAO to the database 2-return a dto from that DAO
        return mapper.CUSTOMER_DTO (
                repository.save (
                        mapper.CUSTOMER_DTO (dto)
                )
        );
    }

    public List<CustomerDto> getAllCustomers() {
        //get the list, turn it to stream, map every DAO to DTO and collect this stream as a list
        return repository.findAll ()
                .stream ()
                .map (mapper::CUSTOMER_DTO)
                .collect (Collectors.toList ());
    }
//if the customer isn't in the database, return an exception
    public CustomerDto getCustomerById(long id) {
        return mapper.CUSTOMER_DTO (repository.findById (id).orElseThrow (() -> new ResourceNotFoundException ("customer", "id", id)));
    }

    public CustomerDto updateCustomer(CustomerDto dto, long id) {
        // get category by id from the database
        Customer customer = repository.findById (id).orElseThrow (() -> new ResourceNotFoundException ("customer", "id", id));
        customer.setFirstname (dto.getFirstname ());
        customer.setLastname (dto.getLastname ());
        customer.setBornAt (dto.getBornAt ());
        return mapper.CUSTOMER_DTO (repository.save (customer));
    }

    public void deleteCustomerById(long id) {
        Customer customer = repository.findById (id).orElseThrow (() -> new ResourceNotFoundException ("customer", "id", id));
        repository.delete (customer);
    }
}
