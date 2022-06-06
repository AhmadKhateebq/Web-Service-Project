package edu.bzu.app.restapi.service.impl;

import edu.bzu.app.restapi.dto.StockDto;
import edu.bzu.app.restapi.entity.Stock;
import edu.bzu.app.restapi.exception.ResourceNotFoundException;
import edu.bzu.app.restapi.repository.StockRepository;
import edu.bzu.app.restapi.service.StockService;
import edu.bzu.app.restapi.utils.MapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    StockRepository repository;
    @Autowired
    MapStruct mapper;

    public StockDto createStock(StockDto dto) {
        return mapper.STOCK_DTO (
                repository.save (
                        mapper.STOCK_DTO (dto)
                )
        );
    }

    public List<StockDto> getAllStocks() {
        return repository.findAll ()
                .stream ()
                .map (mapper::STOCK_DTO)
                .collect(Collectors.toList());
    }

    public StockDto getStockById(long id) {
        return mapper.STOCK_DTO (
                repository.findById (id)
                        .orElseThrow (
                                ()->new ResourceNotFoundException ("customer", "id", id)
                        )
        );
    }

    public StockDto updateStock(StockDto dto, long id) {
        // get category by id from the database
        Stock stock = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("customer", "id", id)
                );
        stock.setUpdateAt (dto.getUpdateAt ());
        stock.setProduct (mapper.PRODUCT_DTO (dto.getProduct ()));
        stock.setQuantity (dto.getQuantity ());

        return mapper.STOCK_DTO (repository.save(stock));
    }

    public void deleteStockById(long id) {
        Stock stock = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("customer", "id", id));
        repository.delete(stock);
    }
}
