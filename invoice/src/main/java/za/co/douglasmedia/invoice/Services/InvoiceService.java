package za.co.douglasmedia.invoice.Services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.douglasmedia.invoice.Entities.Invoice;
import za.co.douglasmedia.invoice.InvoiceApplication;
import za.co.douglasmedia.invoice.Repositories.InvoiceRepository;
import za.co.douglasmedia.invoice.Utils.EntityNotValidException;
import za.co.douglasmedia.invoice.Utils.InvoiceNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    // Add invoice
    public void addInvoice(Invoice invoice) {
        if(validateInvoice(invoice)) {
            invoiceRepository.save(invoice);
        } else {
            throw new EntityNotValidException("Please ensure all invoice fields are filled correctly");
        }
    }

    // Get invoices belonging to a customer
    public Optional<List<Invoice>> getCustomerInvoices(long customerId) {
        Optional<List<Invoice>> invoices = invoiceRepository.findAllInvoicesForCustomerNotDeleted(customerId);
        if(invoices.isEmpty() || invoices.get().isEmpty()){
            throw new InvoiceNotFoundException("No Invoices found");
        } else {
            return invoices;
        }
    }

    // Get invoice by invoiceId
    public Optional<Invoice> getInvoiceById(long invoiceId) {
        Optional<Invoice> invoiceById = invoiceRepository.findInvoiceByIdAndNotDeleted(invoiceId);
        if(invoiceById.isEmpty()) {
            throw new InvoiceNotFoundException("Invoice Not Found");
        } else  {
            return invoiceById;
        }
    }

    // Update invoice
    @Transactional
    public void updateInvoice(Invoice invoice) {
        //InvoiceId, Date Created and CustomerId cannot be updated
        Optional<Invoice> updateInvoice = getInvoiceById(invoice.getInvoiceId());
        if(updateInvoice.isPresent()){
            invoiceRepository.updateInvoiceById(
                    invoice.getAmountDue(),
                    invoice.getStatus(),
                    invoice.getDueDate(),
                    invoice.getTotalCost(),
                    invoice.getAmountPaid(),
                    invoice.isDeleted(),
                    invoice.getInvoiceId()
            );
        } else {
            throw new InvoiceNotFoundException("Invoice " + invoice.getInvoiceId() + " does not exist");
        }

    }

    // Helper Methods
    private boolean validateInvoice(Invoice invoice) {
      // return invoice.getCustomerId() != 0 &&
      //          invoice.getDateCreated() != null &&
       //         invoice.getDueDate() != null &&
       //         invoice.getStatus() != null;
        return true;
    }
}
