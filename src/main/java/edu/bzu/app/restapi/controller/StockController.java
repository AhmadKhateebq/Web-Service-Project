package edu.bzu.app.restapi.controller;

import edu.bzu.app.restapi.dto.StockDto;
import edu.bzu.app.restapi.exception.BadRequestException;
import edu.bzu.app.restapi.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/stock")
public class StockController {
    private final Logger log = LoggerFactory.getLogger (StockController.class);

    @Autowired
    private StockService service;

    @GetMapping
    public ResponseEntity<List<StockDto>> getAll() {
        return ResponseEntity.ok ().body (service.getAllStocks ());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockDto> getById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok (service.getStockById (id));
    }

    @PostMapping
    public ResponseEntity<StockDto> create(@Valid @RequestBody StockDto dto) {
        if (dto.getId () != null) {
            log.error ("Cannot have an ID {}", dto);
            throw new BadRequestException (StockController.class.getSimpleName (),
                    "Id");
        }
        return new ResponseEntity<> (service.createStock (dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StockDto> update(@Valid @RequestBody StockDto dto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<> (service.updateStock (dto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") long id) {
        service.deleteStockById (id);
        return new ResponseEntity<> ("Deleted successfully.", HttpStatus.OK);
    }
}
