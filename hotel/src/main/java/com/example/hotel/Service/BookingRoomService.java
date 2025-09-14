package com.example.hotel.Service;

import java.util.List;
import java.util.Optional;

import com.example.hotel.DTO.BookingRoomDTO;
import com.example.hotel.Model.BookingRoom;

public interface BookingRoomService {

    BookingRoom createBookingRoom(BookingRoomDTO bookingRoomDTO);

    List<BookingRoom> getAllBookingRooms();

    Optional<BookingRoom> getBookingRoomById(Long id);

    BookingRoom updateBookingRoom(Long id, BookingRoomDTO bookingRoomDTO);

    void deleteBookingRoom(Long id);
}
