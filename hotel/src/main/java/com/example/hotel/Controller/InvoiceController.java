package com.example.hotel.Controller;

import java.util.List;
import java.util.Optional;

import javax.naming.Binding;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.ApiResponse;
import com.example.hotel.DTO.InvoiceDTO;
import com.example.hotel.DTO.InvoiceRespone;
import com.example.hotel.DTO.PageRequestDTO;
import com.example.hotel.DTO.PaginationResponse;
import com.example.hotel.DTO.RoomResponse;
import com.example.hotel.Model.Invoice;
import com.example.hotel.Service.InvoiceService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/invoices")
@AllArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @CrossOrigin(origins = "*")
    @PostMapping("/")
    public ApiResponse<InvoiceRespone> createInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ApiResponse.error(errorMessages);
        }
        Invoice invoice = invoiceService.createInvoice(invoiceDTO);
        return ApiResponse.success(InvoiceRespone.fromInvoice(invoice), "Tạo thành công", HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public ApiResponse<List<InvoiceRespone>> getAllInvoice() {
        return ApiResponse.success(invoiceService.getAllInvoice().stream().map(InvoiceRespone::fromInvoice).toList(),
                "thanh cong", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long id) throws Exception {
        invoiceService.deleteInvoice(id);
        return ResponseEntity.ok(String.format("thanh cong"));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ApiResponse<InvoiceRespone> getInvoiceById(@PathVariable Long id) {
        return ApiResponse.success(InvoiceRespone.fromInvoice(invoiceService.getInvoiceById(id)), "Lay thanh cong",
                HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ApiResponse<InvoiceRespone> updateInvoice(@Valid @RequestBody InvoiceDTO invoiceDTO, @PathVariable Long id)
            throws Exception {
        Invoice invoice = invoiceService.updateInvoice(id, invoiceDTO);
        return ApiResponse.success(InvoiceRespone.fromInvoice(invoice), "thanh cong", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/page")
    public ApiResponse<PaginationResponse<InvoiceRespone>> getAllInvoicePage(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        PageRequestDTO pageRequest = new PageRequestDTO(page, size, sortBy, sortDirection);
        var pageInvoice = invoiceService.getAllInvoicePage(pageRequest.toPageable());
        PaginationResponse<InvoiceRespone> paginationResponse = PaginationResponse
                .fromPage(pageInvoice.map(InvoiceRespone::fromInvoice));
        return ApiResponse.success(paginationResponse, "thanh cong");
    }

}
