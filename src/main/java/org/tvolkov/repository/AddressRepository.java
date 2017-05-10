package org.tvolkov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tvolkov.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
