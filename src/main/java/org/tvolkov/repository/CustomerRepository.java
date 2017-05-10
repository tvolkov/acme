package org.tvolkov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tvolkov.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
