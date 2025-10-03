package com.example.hotel.Service.Implement;

import java.util.List;
import java.util.Optional;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.example.hotel.DTO.BookingRoomDTO;
import com.example.hotel.Model.BookingRoom;
import com.example.hotel.Model.Customer;
import com.example.hotel.Model.User;
import com.example.hotel.Repository.BookingRoomRepository;
import com.example.hotel.Repository.CustomerRepository;
import com.example.hotel.Repository.UserRepository;
import com.example.hotel.Service.BookingRoomService;
import com.example.hotel.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder

@AllArgsConstructor
@Service
public class BookingRoomServiceImp implements BookingRoomService {

    private final BookingRoomRepository bookingRoomRepository;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Override
    public BookingRoom createBookingRoom(BookingRoomDTO bookingRoomDTO) {
        Customer existCustomer = customerRepository.findById(bookingRoomDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Không tìm thấy khách hàng với Id = %d", bookingRoomDTO.getCustomerId())));
        User existUser = userRepository.findById(bookingRoomDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Không tìm thấy User với id = %d", bookingRoomDTO.getUserId())));

        try {
            BookingRoom bookingRoom = BookingRoom.builder()
                    .user(existUser)
                    .customer(existCustomer)
                    .dayIn(bookingRoomDTO.getDayIn())
                    .dayOut(bookingRoomDTO.getDayOut())
                    .Status(bookingRoomDTO.getStatus())
                    .sumCost(bookingRoomDTO.getSumCost())
                    .deposit(bookingRoomDTO.getDeposit())
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
    public BookingRoom getBookingRoomById(Long id) {
        return bookingRoomRepository.findById(id).orElseThrow(() -> new RuntimeException("ko thấy"));
    }

    @Override
    public BookingRoom updateBookingRoom(Long id, BookingRoomDTO bookingRoomDTO) {
        Customer customer = customerRepository.findById(bookingRoomDTO.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Không tìm thấy khách hàng với Id = %d", bookingRoomDTO.getCustomerId())));

        User existUser = userRepository.findById(bookingRoomDTO.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Không tìm thấy User với id = %d", bookingRoomDTO.getUserId())));
        BookingRoom bookingRoom = bookingRoomRepository.findById(id).orElseThrow(() -> new RuntimeException("ko thay"));
        bookingRoom.setUser(existUser);
        bookingRoom.setCustomer(customer);
        bookingRoom.setDayIn(bookingRoomDTO.getDayIn());
        bookingRoom.setDayOut(bookingRoomDTO.getDayOut());
        return bookingRoomRepository.save(bookingRoom);

    }

    @Override
    public void deleteBookingRoom(Long id) {
        bookingRoomRepository.deleteById(id);
    }

    @Override
    public Page<BookingRoom> getAllBookingRoomPage(org.springframework.data.domain.Pageable pageable) {
        return bookingRoomRepository.findAll(pageable);
    }

}
