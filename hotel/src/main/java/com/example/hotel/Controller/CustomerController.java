package com.example.hotel.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
import com.example.hotel.DTO.CustomerDTO;
import com.example.hotel.DTO.CustomerResponse;
import com.example.hotel.DTO.InvoiceRespone;
import com.example.hotel.DTO.PageRequestDTO;
import com.example.hotel.DTO.PaginationResponse;
import com.example.hotel.Model.Customer;
import com.example.hotel.Service.CustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @CrossOrigin(origins = "*")
    @PostMapping("/")
    public ApiResponse<CustomerResponse> createCustomer(@Valid @RequestBody CustomerDTO customerDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ApiResponse.error(errorMessages);
        }
        Customer customer = customerService.createCustomer(customerDTO);
        return ApiResponse.success(CustomerResponse.fromCustomer(customer), "thanh cong", HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public ApiResponse<List<CustomerResponse>> getAllCustomer() {
        List<Customer> listCustomers = customerService.getAllCustomer();
        List<CustomerResponse> customerResponses = listCustomers.stream().map(CustomerResponse::fromCustomer).toList();
        return ApiResponse.success(customerResponses, "thanh cong", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ApiResponse<CustomerResponse> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ApiResponse.success(CustomerResponse.fromCustomer(customer), "Lấy thành công", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) throws Exception {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(String.format("xoa thanh cong"));
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ApiResponse<CustomerResponse> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO,
            @PathVariable Long id)
            throws Exception {
        Customer customer = customerService.updateCustomer(id, customerDTO);
        return ApiResponse.success(CustomerResponse.fromCustomer(customer), "thanh cong", HttpStatus.OK);

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/page")
    public ApiResponse<PaginationResponse<CustomerResponse>> getAllInvoicePage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        PageRequestDTO pageRequest = new PageRequestDTO(page, size, sortBy, sortDirection);
        var pageCustomer = customerService.getAllCustomerPage(pageRequest.toPageable());
        PaginationResponse<CustomerResponse> paginationResponse = PaginationResponse
                .fromPage(pageCustomer.map(CustomerResponse::fromCustomer));
        return ApiResponse.success(paginationResponse, "thanh cong");
    }
}
