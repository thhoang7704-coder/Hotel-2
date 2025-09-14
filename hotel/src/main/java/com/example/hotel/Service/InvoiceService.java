package com.example.hotel.Service;

import java.util.List;
import java.util.Optional;

import com.example.hotel.DTO.InvoiceDTO;
import com.example.hotel.Model.Invoice;

public interface InvoiceService {
    Invoice createInvoice(InvoiceDTO invoiceDTO);

    List<Invoice> getAllInvoice();

    void deleteInvoice(Long id) throws Exception;

    Invoice updateInvoice(Long id, InvoiceDTO invoiceDTO) throws Exception;

    Optional<Invoice> getInvoiceById(Long id);
}
