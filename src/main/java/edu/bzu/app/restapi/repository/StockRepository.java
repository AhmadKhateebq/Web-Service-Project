package edu.bzu.app.restapi.repository;

import edu.bzu.app.restapi.entity.Customer;
import edu.bzu.app.restapi.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
