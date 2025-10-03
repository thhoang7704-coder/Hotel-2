package com.example.hotel.Service.Implement;

import org.springframework.stereotype.Service;

import com.example.hotel.Model.BookingRoom;
import com.example.hotel.Model.Invoice;
import com.example.hotel.Model.Payment;
import com.example.hotel.Model.User;
import com.example.hotel.Repository.BookingRoomRepository;
import com.example.hotel.Repository.InvoiceRepository;
import com.example.hotel.Repository.PaymentRepository;
import com.example.hotel.Repository.UserRepository;
import com.example.hotel.Service.PaymentService;
import com.example.hotel.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {
    private final InvoiceRepository invoiceRepository;
    private final PaymentRepository paymentRepository;
    private final BookingRoomRepository bookingRoomRepository;
    private final UserRepository userRepository;

    @Override
    public Payment paymentConfirm(Long idInvoice, String status, String userName) {
        Payment payment = new Payment();
        Invoice existInvoice = invoiceRepository.findById(idInvoice)
                .orElseThrow(() -> new ResourceNotFoundException(""));
        existInvoice.setPaymentStatus(status);
        existInvoice.setStatus("Đã được thanh toán");
        BookingRoom bk = existInvoice.getBookingRoom();
        User us = bk.getUser();
        us.setName(userName);

        userRepository.save(us);
        bookingRoomRepository.save(bk);
        invoiceRepository.save(existInvoice);
        payment.setInvoice(existInvoice);
        return paymentRepository.save(payment);
    }

}
