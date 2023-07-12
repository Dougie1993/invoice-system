package za.co.douglasmedia.invoice.Entities;

import jakarta.persistence.*;

import java.util.Date;

@Entity // maps this class to our database
@Table
public class Invoice {
    @Id
    @SequenceGenerator(
            name = "invoice_sequence",
            sequenceName = "invoice_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "invoice_sequence"
            // how the id and sequence are generated
    )

    private long invoiceId;
    @Temporal(TemporalType.DATE) // To format the date
    private Date dateCreated, dueDate;
    private float amountDue, totalCost, amountPaid;
    private long customerId;
    boolean deleted;

    @Enumerated(EnumType.STRING)
    private Status status;

    // Invoice can only be in 1 of the 5 states at a time
    public enum Status {
        DRAFT,
        APPROVED,
        SENT,
        PAID,
        OVERDUE
    }

    public Invoice() {
    }

    public Invoice(Date dateCreated, Date dueDate, float amountDue, float totalCost, float amountPaid, long customerId, Status status, boolean deleted) {
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.amountDue = amountDue;
        this.totalCost = totalCost;
        this.amountPaid = amountPaid;
        this.customerId = customerId;
        this.status = status;
        this.deleted = deleted;
    }

    public Invoice(long invoiceId, Date dateCreated, Date dueDate, float amountDue, float totalCost, float amountPaid, long customerId, Status status, boolean deleted) {
        this.invoiceId = invoiceId;
        this.dateCreated = dateCreated;
        this.dueDate = dueDate;
        this.amountDue = amountDue;
        this.totalCost = totalCost;
        this.amountPaid = amountPaid;
        this.customerId = customerId;
        this.status = status;
        this.deleted = deleted;
    }

    public long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public float getAmountDue() {
        return amountDue;
    }

    public void setAmountDue(float amountDue) {
        this.amountDue = amountDue;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public float getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(float amountPaid) {
        this.amountPaid = amountPaid;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", dateCreated=" + dateCreated +
                ", dueDate=" + dueDate +
                ", amountDue=" + amountDue +
                ", totalCost=" + totalCost +
                ", amountPaid=" + amountPaid +
                ", customerId=" + customerId +
                ", deleted=" + deleted +
                ", status=" + status +
                '}';
    }
}
