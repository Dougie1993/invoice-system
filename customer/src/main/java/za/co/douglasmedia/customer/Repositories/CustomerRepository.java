package za.co.douglasmedia.customer.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.co.douglasmedia.customer.Entities.Customer;

import java.util.List;
import java.util.Optional;


@Repository // responsible for database and it will work with our service
public interface CustomerRepository extends JpaRepository<Customer, Long> { // Needs type and id type
    // Custom CRUD operations
    @Query("SELECT c FROM Customer c WHERE c.email = ?1 AND c.deleted = false")
    Optional<Customer> findCustomerByEmailAndNotDeleted(String email);

    @Query("SELECT c FROM Customer c WHERE c.custId = ?1 AND c.deleted = false")
    Optional<Customer> findCustomerByIdAndNotDeleted(long id);
    @Query("SELECT c FROM Customer c Where c.deleted = false")
    Optional<List<Customer>> findAllCustomersNotDeleted();

}
