package edu.bzu.app.restapi.controller;

import edu.bzu.app.restapi.dto.OrderDto;
import edu.bzu.app.restapi.exception.BadRequestException;
import edu.bzu.app.restapi.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final Logger log = LoggerFactory.getLogger (OrderController.class);

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll() {
        return ResponseEntity.ok ().body (service.getAllOrders ());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok (service.getOrderById (id));
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto dto) {
        if (dto.getId () != null) {
            log.error ("Cannot have an ID {}", dto);
            throw new BadRequestException (OrderController.class.getSimpleName (),
                    "Id");
        }
        return new ResponseEntity<> (service.createOrder (dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update(@Valid @RequestBody OrderDto dto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<> (service.updateOrder (dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delet(@PathVariable(name = "id") long id) {
        service.deleteOrderById (id);
        return new ResponseEntity<> ("Deleted successfully.", HttpStatus.OK);
    }
}
