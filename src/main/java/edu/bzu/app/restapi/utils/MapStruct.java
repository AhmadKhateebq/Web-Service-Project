package edu.bzu.app.restapi.utils;

import edu.bzu.app.restapi.dto.*;
import edu.bzu.app.restapi.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
//made a mapper from the MapStruct mapper, to map every entity to DTO
@Mapper(componentModel = "spring")
public interface MapStruct {

    MapStruct INSTANCE = Mappers.getMapper(MapStruct.class);
//this mapper build these methods using the setters and the getters of each class, this implementation
    //can be found in "target/generated-sources/**/MapStructImpl.java" after building the project
    //used same name for the methods with different signature to make it easier to use
    CustomerDto CUSTOMER_DTO(Customer customer);
    Customer CUSTOMER_DTO(CustomerDto dto);

    OrderDto ORDER_DTO(Order order);
    Order ORDER_DTO(OrderDto dto);

    ProductDto PRODUCT_DTO(Product product);
    Product PRODUCT_DTO(ProductDto dto);

    ProductOrderDto PRODUCT_ORDER_DTO(ProductOrder productOrder);
    ProductOrder PRODUCT_ORDER_DTO(ProductOrderDto dto);

    StockDto STOCK_DTO(Stock stock);
    Stock STOCK_DTO(StockDto dto);

}
