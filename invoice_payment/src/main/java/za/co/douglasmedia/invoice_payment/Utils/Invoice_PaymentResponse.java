package za.co.douglasmedia.invoice_payment.Utils;

public class Invoice_PaymentResponse {
    private int status;
    private String message;

    public Invoice_PaymentResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
