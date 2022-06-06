package edu.bzu.app.restapi.controller;

import edu.bzu.app.restapi.dto.ProductOrderDto;
import edu.bzu.app.restapi.exception.BadRequestException;
import edu.bzu.app.restapi.service.ProductOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/productOrder")
public class ProductOrderController {
    private final Logger log = LoggerFactory.getLogger (ProductOrderController.class);

    @Autowired
    private ProductOrderService service;

    @GetMapping
    public ResponseEntity<List<ProductOrderDto>> getAllC() {
        return ResponseEntity.ok ().body (service.getAllProductOrders ());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductOrderDto> getById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok (service.getProductOrderById (id));
    }

    @PostMapping
    public ResponseEntity<ProductOrderDto> create(@Valid @RequestBody ProductOrderDto dto) {
        if (dto.getId () != null) {
            log.error ("Cannot have an ID {}", dto);
            throw new BadRequestException (ProductOrderController.class.getSimpleName (),
                    "Id");
        }
        return new ResponseEntity<> (service.createProductOrder (dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductOrderDto> update(@Valid @RequestBody ProductOrderDto dto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<> (service.updateProductOrder (dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") long id) {
        service.deleteProductOrderById (id);
        return new ResponseEntity<> ("Deleted successfully.", HttpStatus.OK);
    }
}
