package com.example.hotel.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.hotel.DTO.InvoiceDTO;
import com.example.hotel.Model.Invoice;

public interface InvoiceService {
    Invoice createInvoice(InvoiceDTO invoiceDTO);

    List<Invoice> getAllInvoice();

    void deleteInvoice(Long id) throws Exception;

    Invoice updateInvoice(Long id, InvoiceDTO invoiceDTO) throws Exception;

    Invoice getInvoiceById(Long id);

    Page<Invoice> getAllInvoicePage(Pageable pageable);
}
