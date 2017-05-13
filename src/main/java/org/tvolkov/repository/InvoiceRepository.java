package org.tvolkov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tvolkov.model.Invoice;

import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

    @Query("select i from Invoice i where i.address.customer.id = ?1")
    List<Invoice> findByCustomerId(int customerId);

    @Query("select i from Invoice i where i.address.customer.id = ?1 and i.address.id = ?2")
    List<Invoice> findByCustomerIdAndAddressId(int customerId, int addressId);

    @Query("select i from Invoice i where i.address.customer.id = ?1 and i.month = ?2 and i.invoiceType = ?3")
    List<Invoice> findByCustomerIdMonthAndType(int customerId, int month, int type);
}
