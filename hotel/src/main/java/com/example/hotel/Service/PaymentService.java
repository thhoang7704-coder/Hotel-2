package com.example.hotel.Service;

import com.example.hotel.Model.Payment;

public interface PaymentService {
    Payment paymentConfirm(Long idInvoice, String status, String userName);
}
