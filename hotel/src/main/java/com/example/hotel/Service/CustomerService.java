package com.example.hotel.Service;

import java.util.List;
import java.util.Optional;

import com.example.hotel.DTO.CustomerDTO;
import com.example.hotel.Model.Customer;

public interface CustomerService {
    Customer createCustomer(CustomerDTO customerDTO);

    List<Customer> getAllCustomer();

    void deleteCustomer(Long id) throws Exception;

    Customer updateCustomer(Long id, CustomerDTO customerDTO) throws Exception;

    Customer getCustomerById(Long id);
}
