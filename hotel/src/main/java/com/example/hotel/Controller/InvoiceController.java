package com.example.hotel.Controller;

import java.util.List;
import java.util.Optional;

import javax.naming.Binding;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.CustomerResponse;
import com.example.hotel.DTO.InvoiceDTO;
import com.example.hotel.DTO.InvoiceRespone;
import com.example.hotel.Model.Invoice;
import com.example.hotel.Service.InvoiceService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/invoices")
@AllArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @PostMapping("/")
    public ResponseEntity<?> createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        Invoice invoice = invoiceService.createInvoice(invoiceDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body((InvoiceRespone.fromInvoice(invoice)));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllInvoice() {
        return ResponseEntity.ok(invoiceService.getAllInvoice().stream().map(InvoiceRespone::fromInvoice).toString());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long id) throws Exception {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.ok(String.format("thanh cong"));
    }

    @GetMapping("/{id}")
    public Optional<Invoice> getInvoiceById(@PathVariable Long id) {
        return invoiceService.getInvoiceById(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO, @PathVariable Long id) {
        Invoice invoice = invoiceService.updateInvoice(id, invoiceDTO);
        return ResponseEntity.ok(invoice);
    }
}
