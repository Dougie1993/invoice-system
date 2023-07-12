package za.co.douglasmedia.invoice.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.douglasmedia.invoice.Entities.Invoice;
import za.co.douglasmedia.invoice.Services.InvoiceService;
import za.co.douglasmedia.invoice.Utils.EntityNotValidException;
import za.co.douglasmedia.invoice.Utils.InvoiceNotFoundException;
import za.co.douglasmedia.invoice.Utils.InvoiceResponse;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "invoice")
public class InvoiceController {
    private final InvoiceService invoiceService;

    @Autowired
    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // Get invoices for customer
    @GetMapping("customer/{customerId}")
    public ResponseEntity<Optional<List<Invoice>>> getCustomerInvoices(@PathVariable("customerId") long customerId){
        Optional<List<Invoice>> invoices;
        invoices = invoiceService.getCustomerInvoices(customerId);
        return ResponseEntity.ok(invoices);
    }

    // Get invoice by Id
    @GetMapping("invoice/{invoiceId}")
    public ResponseEntity<Optional<Invoice>> getInvoiceById(@PathVariable("invoiceId") long invoiceId){
        Optional<Invoice> invoice;
        invoice = invoiceService.getInvoiceById(invoiceId);
        return ResponseEntity.ok(invoice);
    }

    // Add Invoice
    @PostMapping
    public ResponseEntity<InvoiceResponse> addInvoice(@RequestBody Invoice invoice){
        invoiceService.addInvoice(invoice);
        InvoiceResponse response = new InvoiceResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Invoice Saved");

        return ResponseEntity.ok(response);

    }

    // Update Invoice
    @PutMapping("/invoice")
    public ResponseEntity<InvoiceResponse> updateInvoice(@RequestBody Invoice invoice){
        invoiceService.updateInvoice(invoice);
        InvoiceResponse response = new InvoiceResponse();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Invoice Updated");

        return ResponseEntity.ok(response);

    }
    // Exception handlers to format response
    @ExceptionHandler(InvoiceNotFoundException.class)
    public  ResponseEntity<InvoiceResponse> handleInvoiceNotFoundException(InvoiceNotFoundException ex) {
        InvoiceResponse response = new InvoiceResponse();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        response.setMessage(ex.getMessage());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(EntityNotValidException.class)
    public ResponseEntity<InvoiceResponse> handleEntityNotValidException(EntityNotValidException ex){
        InvoiceResponse response = new InvoiceResponse();
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(ex.getMessage());

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


}
