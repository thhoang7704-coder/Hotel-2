package com.example.hotel.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.CustomerDTO;
import com.example.hotel.DTO.CustomerResponse;
import com.example.hotel.Model.Customer;
import com.example.hotel.Service.CustomerService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/")
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDTO customerDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        Customer customer = customerService.createCustomer(customerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body((CustomerResponse.fromCustomer(customer)));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllCustomer() {
        List<Customer> listCustomers = customerService.getAllCustomer();
        List<CustomerResponse> customerResponses = listCustomers.stream().map(CustomerResponse::fromCustomer).toList();
        return ResponseEntity.ok(customerResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) throws Exception {
        customerService.deleteCustomer(id);
        return ResponseEntity.ok(String.format("xoa thanh cong"));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable Long id)
            throws Exception {
        Customer customer = customerService.updateCustomer(id, customerDTO);
        return ResponseEntity.ok(customer);

    }

}
