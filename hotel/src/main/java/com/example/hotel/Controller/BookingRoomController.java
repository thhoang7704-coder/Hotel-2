package com.example.hotel.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.BookingRoomDTO;
import com.example.hotel.DTO.BookingRoomRespone;
import com.example.hotel.Model.BookingRoom;
import com.example.hotel.Service.BookingRoomService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/bookingrooms")
@AllArgsConstructor
public class BookingRoomController {
    private final BookingRoomService bookingRoomService;

    @PostMapping("/")
    public ResponseEntity<?> createBookingRoom(@Valid @RequestBody BookingRoomDTO bookingRoomDTO BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
        BookingRoom bookingRoom = bookingRoomService.createBookingRoom(bookingRoomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body((BookingRoomRespone.fromBookingRoom(bookingRoom)));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllBookingRoom(@Valid @RequestBody BookingRoomDTO bookingRoomDTO,
            @PathVariable Long id) {
        List<BookingRoom> bookingRooms = bookingRoomService.getAllBookingRooms();
        return ResponseEntity.ok(bookingRooms);
    }

    @GetMapping("/{id}")
    public Optional<BookingRoom> getBookingRoomById(@PathVariable Long id) {
        return bookingRoomService.getBookingRoomById(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateBookingRoom(@Valid @RequestBody BookingRoomDTO bookingRoomDTO,
            @PathVariable Long id) {
        BookingRoom bookingRoom = bookingRoomService.updateBookingRoom(id, bookingRoomDTO);
        return ResponseEntity.ok(bookingRoom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookingRoom(@PathVariable Long id) {
        bookingRoomService.deleteBookingRoom(id);
        return ResponseEntity.ok(String.format("thanh cong"));
    }

}
