package com.example.hotel.DTO;

import java.sql.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InvoiceDTO {

    @NotNull(message = "BookingRoomId không được null")
    private Long bookingRoomId;

    @NotBlank(message = "Phương thức thanh toán không được để trống")
    private String methodPay;

    @NotNull(message = "Ngày thanh toán không được null")
    private Date datePayment;

    @Min(value = 0, message = "Tổng tiền phải >= 0")
    private double totalCost;
}
