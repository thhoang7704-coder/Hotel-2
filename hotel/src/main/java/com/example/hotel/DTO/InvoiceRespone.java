package com.example.hotel.DTO;

import java.sql.Date;

import com.example.hotel.Model.Invoice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceRespone {
    private BookingRoomRespone bookingRoomRespone;
    private Long id;
    private Long bookingRoomId;
    private String methodPay;
    private Date datePayment;
    private double totalCost;
    private String status;
    private String paymentStatus;

    public static InvoiceRespone fromInvoice(Invoice invoice) {
        InvoiceRespone invoiceRespone = InvoiceRespone.builder()
                .bookingRoomRespone(BookingRoomRespone.fromBookingRoom(invoice.getBookingRoom()))
                .id(invoice.getId())
                .bookingRoomId(invoice.getBookingRoom().getId())
                .methodPay(invoice.getMethodPay())
                .datePayment(invoice.getDatePayment())
                .totalCost(invoice.getTotalCost())
                .status(invoice.getStatus())
                .paymentStatus(invoice.getPaymentStatus())
                .build();
        return invoiceRespone;
    }

}
