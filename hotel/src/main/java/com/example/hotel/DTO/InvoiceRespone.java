package com.example.hotel.DTO;

import java.sql.Date;

import com.example.hotel.Model.Invoice;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InvoiceRespone {
    public Long bookingRoomId;
    public String methodPay;
    public Date datePayment;

    public static InvoiceRespone fromInvoice(Invoice invoice) {
        InvoiceRespone invoiceRespone = InvoiceRespone.builder()
                .bookingRoomId(invoice.getBookingRoomId())
                .methodPay(invoice.getMethodPay())
                .datePayment(invoice.getDatePayment())
                .build();
        return invoiceRespone;
    }

}
