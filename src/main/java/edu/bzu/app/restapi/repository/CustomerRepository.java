package edu.bzu.app.restapi.repository;

import edu.bzu.app.restapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//this @ is not needed here because it extends the JpaRepository, but its added to
//remove the warning in autowiring, Spring boot auto implement the statements of the methods in the super class
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
