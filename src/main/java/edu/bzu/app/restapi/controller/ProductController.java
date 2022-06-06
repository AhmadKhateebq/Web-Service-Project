package edu.bzu.app.restapi.controller;

import edu.bzu.app.restapi.dto.ProductDto;
import edu.bzu.app.restapi.exception.BadRequestException;
import edu.bzu.app.restapi.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    private final Logger log = LoggerFactory.getLogger (ProductController.class);

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok ().body (service.getAllProducts ());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok (service.getProductById (id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@Valid @RequestBody ProductDto dto) {
        if (dto.getId () != null) {
            log.error ("Cannot have an ID {}", dto);
            throw new BadRequestException (ProductController.class.getSimpleName (),
                    "Id");
        }
        return new ResponseEntity<> (service.createProduct (dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> update(@Valid @RequestBody ProductDto dto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<> (service.updateProduct (dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") long id) {
        service.deleteProductById (id);
        return new ResponseEntity<> ("Deleted successfully.", HttpStatus.OK);
    }
}
