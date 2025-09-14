package com.example.hotel.DTO;

import java.sql.Date;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRoomDTO {
    private Long userId;
    private Long customerId;
    private Date dayIn;
    private Date dayOut;
    private String Status;
    private double sumCost;
    private double deposit; // tiền cọc
}
