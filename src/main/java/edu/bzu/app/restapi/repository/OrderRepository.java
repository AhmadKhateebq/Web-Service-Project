package edu.bzu.app.restapi.repository;

import edu.bzu.app.restapi.entity.Customer;
import edu.bzu.app.restapi.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
