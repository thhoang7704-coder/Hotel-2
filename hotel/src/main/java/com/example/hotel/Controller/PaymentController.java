package com.example.hotel.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.ApiResponse;
import com.example.hotel.Model.Payment;
import com.example.hotel.Service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ApiResponse<Payment> paymentConfirm(@RequestParam Long idInvoice, @RequestParam String status,
            @RequestParam String userName) {
        return ApiResponse.success(paymentService.paymentConfirm(idInvoice, status, userName), "Thanh cong",
                HttpStatus.CREATED);
    }

}
