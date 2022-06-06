package edu.bzu.app.restapi.controller;

import edu.bzu.app.restapi.dto.CustomerDto;
import edu.bzu.app.restapi.exception.BadRequestException;
import edu.bzu.app.restapi.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//I will just document one class because all the other classed are alike
@RestController//to enable the class as a controller class to handle the requests
@RequestMapping("/api/customer")//to make the class listen to request on this URL
public class CustomerController {
    //Logger to Make logs on the console in case if errors
    private final Logger log = LoggerFactory.getLogger (CustomerController.class);

    //auto wiring the service from CustomerService bean
    @Autowired
    private CustomerService service;

    //handle the GET method request at / uri to return all customers
    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll() {
        return ResponseEntity.ok ().body (service.getAllCustomers ());
    }
    //handle the GET method request at / uri to return customer with that id
    //path variable as /1 to get at id = 1
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok (service.getCustomerById (id));
    }

    //take CustomerDto as a body , and add it to the database
    @PostMapping
    public ResponseEntity<CustomerDto> create(@Valid @RequestBody CustomerDto dto) {
        if (dto.getId () != null) {
            log.error ("Cannot have an ID {}", dto);
            throw new BadRequestException (CustomerController.class.getSimpleName (),
                    "Id");
        }
        return new ResponseEntity<> (service.createCustomer (dto), HttpStatus.CREATED);
    }

    //to update a customer
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> update(@Valid @RequestBody CustomerDto dto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<> (service.updateCustomer (dto, id), HttpStatus.OK);
    }

    //to delete a customer
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") long id) {
        service.deleteCustomerById (id);
        return new ResponseEntity<> ("Deleted successfully.", HttpStatus.OK);
    }
}
