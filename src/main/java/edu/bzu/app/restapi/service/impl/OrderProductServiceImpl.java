package edu.bzu.app.restapi.service.impl;

import edu.bzu.app.restapi.dto.ProductOrderDto;
import edu.bzu.app.restapi.entity.ProductOrder;
import edu.bzu.app.restapi.exception.ResourceNotFoundException;
import edu.bzu.app.restapi.repository.ProductOrderRepository;
import edu.bzu.app.restapi.service.ProductOrderService;
import edu.bzu.app.restapi.utils.MapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderProductServiceImpl implements ProductOrderService {
    @Autowired
    ProductOrderRepository repository;
    @Autowired
    MapStruct mapper;

    public ProductOrderDto createProductOrder(ProductOrderDto dto) {
        return mapper.PRODUCT_ORDER_DTO (
                repository.save (
                        mapper.PRODUCT_ORDER_DTO (dto)
                )
        );
    }

    public List<ProductOrderDto> getAllProductOrders() {
        return repository.findAll ()
                .stream ()
                .map (mapper::PRODUCT_ORDER_DTO)
                .collect(Collectors.toList());
    }

    public ProductOrderDto getProductOrderById(long id) {
        return mapper.PRODUCT_ORDER_DTO (
                repository.findById (id)
                        .orElseThrow (
                                ()->new ResourceNotFoundException ("ProductOrder", "id", id)
                        )
        );
    }

    public ProductOrderDto updateProductOrder(ProductOrderDto dto, long id) {
        // get category by id from the database
        ProductOrder order = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("ProductOrder", "id", id)
                );
        order.setOrder (mapper.ORDER_DTO (dto.getOrder ()));
        order.setProduct (mapper.PRODUCT_DTO (dto.getProduct ()));
        order.setPrice (dto.getPrice ());
        order.setQuantity (dto.getQuantity ());
        order.setVat (dto.getVat ());
        return mapper.PRODUCT_ORDER_DTO (repository.save(order));
    }

    public void deleteProductOrderById(long id) {
        ProductOrder order = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ProductOrder", "id", id));
        repository.delete(order);
    }
}
