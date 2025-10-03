package com.example.hotel.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.hotel.DTO.BookingRoomDTO;
import com.example.hotel.Model.BookingRoom;

public interface BookingRoomService {

    BookingRoom createBookingRoom(BookingRoomDTO bookingRoomDTO);

    List<BookingRoom> getAllBookingRooms();

    BookingRoom getBookingRoomById(Long id);

    BookingRoom updateBookingRoom(Long id, BookingRoomDTO bookingRoomDTO);

    void deleteBookingRoom(Long id);

    Page<BookingRoom> getAllBookingRoomPage(Pageable pageable);
}
