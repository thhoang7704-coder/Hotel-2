package com.example.hotel.DTO;

import java.sql.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDTO {
    private Long bookingRoomId;
    private String methodPay;
    private Date datePayment;
    private double totalCost;
}
