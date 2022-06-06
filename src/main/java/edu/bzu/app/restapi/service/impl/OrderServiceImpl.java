package edu.bzu.app.restapi.service.impl;

import edu.bzu.app.restapi.dto.OrderDto;
import edu.bzu.app.restapi.entity.Order;
import edu.bzu.app.restapi.exception.ResourceNotFoundException;
import edu.bzu.app.restapi.repository.OrderRepository;
import edu.bzu.app.restapi.service.OrderService;
import edu.bzu.app.restapi.utils.MapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository repository;
    @Autowired
    MapStruct mapper;

    public OrderDto createOrder(OrderDto dto) {
        return mapper.ORDER_DTO (
                repository.save (
                        mapper.ORDER_DTO (dto)
                )
        );
    }

    public List<OrderDto> getAllOrders() {
        return repository.findAll ()
                .stream ()
                .map (mapper::ORDER_DTO)
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(long id) {
        return mapper.ORDER_DTO (
                repository.findById (id)
                        .orElseThrow (
                                ()->new ResourceNotFoundException ("customer", "id", id)
                        )
        );
    }

    public OrderDto updateOrder(OrderDto dto, long id) {
        // get category by id from the database
        Order order = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("customer", "id", id)
                );
        order.setOrderedAt (dto.getOrderedAt ());
        order.setCustomer (mapper.CUSTOMER_DTO (dto.getCustomer ()));
        return mapper.ORDER_DTO (repository.save(order));
    }

    public void deleteOrderById(long id) {
        Order order = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("customer", "id", id));
        repository.delete(order);
    }
}
