package edu.bzu.app.restapi.service.impl;

import edu.bzu.app.restapi.dto.ProductDto;
import edu.bzu.app.restapi.entity.Product;
import edu.bzu.app.restapi.exception.ResourceNotFoundException;
import edu.bzu.app.restapi.repository.ProductRepository;
import edu.bzu.app.restapi.service.ProductService;
import edu.bzu.app.restapi.utils.MapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository repository;
    @Autowired
    MapStruct mapper;

    public ProductDto createProduct(ProductDto dto) {
        return mapper.PRODUCT_DTO (
                repository.save (
                        mapper.PRODUCT_DTO (dto)
                )
        );
    }

    public List<ProductDto> getAllProducts() {
        return repository.findAll ()
                .stream ()
                .map (mapper::PRODUCT_DTO)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(long id) {
        return mapper.PRODUCT_DTO (
                repository.findById (id)
                        .orElseThrow (
                                ()->new ResourceNotFoundException ("customer", "id", id)
                        )
        );
    }

    public ProductDto updateProduct(ProductDto dto, long id) {
        // get category by id from the database
        Product product = repository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("customer", "id", id)
                );

        product.setPrice (dto.getPrice ());
        product.setName (dto.getName ());
        product.setReference (dto.getReference ());
        product.setSlug (dto.getSlug ());
        product.setStackable (dto.isStackable ());
        product.setVat (dto.getVat ());
        return mapper.PRODUCT_DTO (repository.save(product));
    }

    public void deleteProductById(long id) {
        Product product = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("customer", "id", id));
        repository.delete(product);
    }
}
