package com.example.hotel.DTO;

import com.example.hotel.Model.Customer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {
    private String name;
    private Long id;

    public static CustomerResponse fromCustomer(Customer customer) {
        CustomerResponse customerResponse = CustomerResponse.builder()
                .name(customer.getName())

                .build();
        return customerResponse;
    }

}
