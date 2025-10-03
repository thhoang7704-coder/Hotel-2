package com.example.hotel.DTO;

import java.sql.Date;

import com.example.hotel.Model.BookingRoom;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRoomRespone {
    private Long id;
    private CustomerResponse customerResponse;
    private UserResponse userResponse;
    private Date dayIn;
    private Date dayOut;
    private double deposit;
    private String Status;
    private double sumCost;

    public static BookingRoomRespone fromBookingRoom(BookingRoom bookingRoom) {
        BookingRoomRespone bookingRoomRespone = BookingRoomRespone.builder()
                .id(bookingRoom.getId())
                .customerResponse(CustomerResponse.fromCustomer(bookingRoom.getCustomer()))
                .userResponse(UserResponse.fromUser(bookingRoom.getUser()))
                .dayIn(bookingRoom.getDayIn())
                .dayOut(bookingRoom.getDayOut())
                .deposit(bookingRoom.getDeposit())
                .Status(bookingRoom.getStatus())
                .sumCost(bookingRoom.getSumCost())
                .build();
        return bookingRoomRespone;
    }

    {

    }
}
