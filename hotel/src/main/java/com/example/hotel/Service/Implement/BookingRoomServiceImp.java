package com.example.hotel.Service.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.hotel.DTO.BookingRoomDTO;
import com.example.hotel.Model.BookingRoom;
import com.example.hotel.Repository.BookingRoomRepository;
import com.example.hotel.Service.BookingRoomService;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Service
public class BookingRoomServiceImp implements BookingRoomService {

    private final BookingRoomRepository bookingRoomRepository;

    @Override
    public BookingRoom createBookingRoom(BookingRoomDTO bookingRoomDTO) {
        try {
            BookingRoom bookingRoom = BookingRoom.builder()
                    .userId(bookingRoomDTO.getUserId())
                    .customerId(bookingRoomDTO.getCustomerId())
                    .dayIn(bookingRoomDTO.getDayIn())
                    .dayOut(bookingRoomDTO.getDayOut())

                    .build();
            bookingRoomRepository.save(bookingRoom);
            return bookingRoom;
        } catch (Exception e) {
            throw new RuntimeException("Không thể tạo user" + e.getMessage());
        }
    }

    @Override
    public List<BookingRoom> getAllBookingRooms() {
        List<BookingRoom> bookingRooms = bookingRoomRepository.findAll();
        return bookingRooms;
    }

    @Override
    public Optional<BookingRoom> getBookingRoomById(Long id) {
        return bookingRoomRepository.findById(id);
    }

    @Override
    public BookingRoom updateBookingRoom(Long id, BookingRoomDTO bookingRoomDTO) {
        BookingRoom bookingRoom = bookingRoomRepository.findById(id).orElseThrow(() -> new RuntimeException("ko thay"));
        BookingRoom.builder()
                .userId(bookingRoomDTO.getUserId())
                .customerId(bookingRoomDTO.getCustomerId())
                .dayIn(bookingRoomDTO.getDayIn())
                .dayOut(bookingRoomDTO.getDayOut())

                .build();
        bookingRoomRepository.save(bookingRoom);
        return bookingRoom;
    }

    @Override
    public void deleteBookingRoom(Long id) {
        bookingRoomRepository.deleteById(id);
    }

}
