package com.example.hotel.Service.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.hotel.DTO.CustomerDTO;
import com.example.hotel.Model.Customer;
import com.example.hotel.Repository.CustomerRepository;
import com.example.hotel.Service.CustomerService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor

public class CustomerServiceImp implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(CustomerDTO customerDTO) {
        try {
            Customer customer = Customer.builder()
                    .name(customerDTO.getName())
                    .dob(customerDTO.getDob())
                    .email(customerDTO.getEmail())
                    .identifier(customerDTO.getIdentifier())
                    .build(); // đọc bản thiết kế rồi chỉ điểm
            // new Customer();
            // customer.setName(customerDTO.getName());
            // customer.setDob(customerDTO.getDob());
            // customer.setEmail(customerDTO.getEmail());
            // customer.setIdentifier(customerDTO.getIdentifier());
            customerRepository.save(customer);
            return customer;
        } catch (Exception e) {
            throw new RuntimeException("Không thể tạo user" + e.getMessage());
        }
    }

    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> listCustomer = customerRepository.findAll();
        return listCustomer;
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer updateCustomer(Long id, CustomerDTO customerDTO) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new RuntimeException("Ko thay id"));
        customer.setName(customerDTO.getName());
        customer.setDob(customerDTO.getDob());
        customer.setEmail(customerDTO.getEmail());
        customer.setIdentifier(customerDTO.getIdentifier());

        return customerRepository.save(customer);

    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElseThrow(() -> new RuntimeException("ko thay id"));
    }

    @Override
    public Page<Customer> getAllCustomerPage(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

}
