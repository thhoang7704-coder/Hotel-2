package com.example.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.Model.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

}
