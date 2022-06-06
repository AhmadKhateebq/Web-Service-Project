package edu.bzu.app.restapi.repository;

import edu.bzu.app.restapi.entity.Customer;
import edu.bzu.app.restapi.entity.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, Long> {

}
