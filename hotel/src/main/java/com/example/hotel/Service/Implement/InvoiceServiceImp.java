package com.example.hotel.Service.Implement;

import java.util.List;
import java.util.Optional;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.hotel.DTO.InvoiceDTO;
import com.example.hotel.Model.BookingRoom;
import com.example.hotel.Model.Invoice;
import com.example.hotel.Model.Room;
import com.example.hotel.Repository.BookingRoomRepository;
import com.example.hotel.Repository.InvoiceRepository;
import com.example.hotel.Service.InvoiceService;
import com.example.hotel.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InvoiceServiceImp implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final BookingRoomRepository bookingRoomRepository;

    @Override
    public Invoice createInvoice(InvoiceDTO invoiceDTO) {
        BookingRoom existBookingRoom = bookingRoomRepository.findById(invoiceDTO.getBookingRoomId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Ko tìm thấy hóa đơn với id = $d", invoiceDTO.getBookingRoomId())));
        Invoice invoice = Invoice.builder()
                .bookingRoom(existBookingRoom)
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
        BookingRoom existBookingRoom = bookingRoomRepository.findById(invoiceDTO.getBookingRoomId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Ko tìm thấy hóa đơn với id = $d", invoiceDTO.getBookingRoomId())));

        invoice.setBookingRoom(existBookingRoom);
        invoice.setMethodPay(invoiceDTO.getMethodPay());
        invoice.setDatePayment(invoiceDTO.getDatePayment());
        invoice.setTotalCost(invoiceDTO.getTotalCost());

        return invoiceRepository.save(invoice);

    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("ko thay"));
    }

    @Override
    public Page<Invoice> getAllInvoicePage(org.springframework.data.domain.Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

}
