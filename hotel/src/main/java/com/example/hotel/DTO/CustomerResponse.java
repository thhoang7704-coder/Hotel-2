package com.example.hotel.DTO;

import java.time.LocalDate;

import com.example.hotel.Model.Customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private String name;
    private Long id;

    private LocalDate dob;
    private String email;
    private String identifier;

    public static CustomerResponse fromCustomer(Customer customer) {
        CustomerResponse customerResponse = CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .dob(customer.getDob())
                .email(customer.getEmail())
                .identifier(customer.getIdentifier())
                .build();
        return customerResponse;
    }

}
