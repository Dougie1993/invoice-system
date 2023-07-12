package za.co.douglasmedia.invoice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import za.co.douglasmedia.invoice.Entities.Invoice;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    @Query("SELECT i FROM Invoice i Where i.deleted = false AND i.customerId =?1")
    Optional<List<Invoice>> findAllInvoicesForCustomerNotDeleted(long customerId);

    @Query("SELECT i FROM Invoice i WHERE i.invoiceId = ?1 AND i.deleted = false")
    Optional<Invoice> findInvoiceByIdAndNotDeleted(long invoiceId);

    @Modifying
    @Query("UPDATE Invoice i SET i.amountDue = ?1, i.status = ?2, i.dueDate = ?3, i.totalCost =?4, i.amountPaid =?5, i.deleted = ?6 WHERE i.invoiceId = ?7 AND i.deleted = false")
    void updateInvoiceById(float amountDue, Invoice.Status status, Date dueDate, float totalCost, float amountPaid, boolean deleted, long invoiceId);
}
