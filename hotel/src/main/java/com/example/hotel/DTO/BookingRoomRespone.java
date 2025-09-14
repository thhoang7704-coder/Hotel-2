package com.example.hotel.DTO;

import java.sql.Date;

import com.example.hotel.Model.BookingRoom;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingRoomRespone {
    public Long customerId;
    public Date dayIn;
    public Date dayOut;
    public double deposit;

    public static BookingRoomRespone fromBookingRoom(BookingRoom bookingRoom) {
        BookingRoomRespone bookingRoomRespone = BookingRoomRespone.builder()
                .customerId(bookingRoom.getCustomerId())
                .dayIn(bookingRoom.getDayIn())
                .dayOut(bookingRoom.getDayOut())
                .deposit(bookingRoom.getDeposit())
                .build();
        return bookingRoomRespone;
    }

    {

    }
}
