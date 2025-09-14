package com.example.hotel.Service.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hotel.DTO.InvoiceDTO;
import com.example.hotel.Model.Invoice;
import com.example.hotel.Repository.InvoiceRepository;
import com.example.hotel.Service.InvoiceService;
import com.example.hotel.Service.User;

@Service
public class InvoiceServiceImp implements InvoiceService {
    private final InvoiceRepository invoiceRepository;

    @Override
    public Invoice createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = Invoice.builder()
                .bookingRoomId(invoiceDTO.getBookingRoomId())
                .methodPay(invoiceDTO.getMethodPay())
                .datePayment(invoiceDTO.getDatePayment())
                .totalCost(invoiceDTO.getTotalCost())
                .build();
        invoiceRepository.save(invoice);
        return invoice;
    }

    @Override
    public List<Invoice> getAllInvoice() {
        List<Invoice> invoice = invoiceRepository.findAll();
        return invoice;
    }

    @Override
    public void deleteInvoice(Long id) throws Exception {
        invoiceRepository.deleteById(id);
    }

    @Override
    public Invoice updateInvoice(Long id, InvoiceDTO invoiceDTO) throws Exception {
        Invoice invoice = invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("ko thay"));
        Invoice.builder()
                .bookingRoomId(invoiceDTO.getBookingRoomId())
                .methodPay(invoiceDTO.getMethodPay())
                .datePayment(invoiceDTO.getDatePayment())
                .totalCost(invoiceDTO.getTotalCost())
                .build();
        invoiceRepository.save(invoice);
        return invoice;

    }

    @Override
    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

}
