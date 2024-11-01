package mx.com.bhit.micro.model;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class PaymentDetails {
    private String paymentMethod;
    private String transactionId;
    private LocalDateTime paymentDate;
    private Double amount;

    public PaymentDetails() {
    }
}
