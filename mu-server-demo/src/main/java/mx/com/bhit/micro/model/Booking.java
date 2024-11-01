package mx.com.bhit.micro.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.json.JSONObject;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class Booking {
    private Long id;
    private Long userName;
    private Long propertyId;
    private Long tableSize;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private BookingStatus status;
    private Double totalCost;
    private PaymentDetails paymentDetails;


    public Booking() {
    }

    // Enum para el estado de la reserva
    public enum BookingStatus {
        PENDING,
        CONFIRMED,
        CANCELLED,
        COMPLETED
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserName() {
        return userName;
    }

    public void setUserName(Long userName) {
        this.userName = userName;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getTableSize() {
        return tableSize;
    }

    public void setTableSize(Long tableSize) {
        this.tableSize = tableSize;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(Double totalCost) {
        this.totalCost = totalCost;
    }

    public PaymentDetails getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(PaymentDetails paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public JSONObject toJSON() {
        return new JSONObject() {
        }
                .put("userName", userName)
                .put("tableSize", tableSize);
    }
}
