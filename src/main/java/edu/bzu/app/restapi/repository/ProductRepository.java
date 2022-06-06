package edu.bzu.app.restapi.repository;

import edu.bzu.app.restapi.entity.Customer;
import edu.bzu.app.restapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
