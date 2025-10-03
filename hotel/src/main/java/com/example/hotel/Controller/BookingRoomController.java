package com.example.hotel.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.ApiResponse;
import com.example.hotel.DTO.BookingRoomDTO;
import com.example.hotel.DTO.BookingRoomRespone;
import com.example.hotel.DTO.InvoiceRespone;
import com.example.hotel.DTO.PageRequestDTO;
import com.example.hotel.DTO.PaginationResponse;
import com.example.hotel.Model.BookingRoom;
import com.example.hotel.Service.BookingRoomService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/bookingrooms")
@AllArgsConstructor
public class BookingRoomController {
    private final BookingRoomService bookingRoomService;

    @CrossOrigin(origins = "*")
    @PostMapping("/")
    public ApiResponse<BookingRoomRespone> createBookingRoom(@Valid @RequestBody BookingRoomDTO bookingRoomDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage).toList();
            return ApiResponse.error(errorMessages);
        }
        BookingRoom bookingRoom = bookingRoomService.createBookingRoom(bookingRoomDTO);
        return ApiResponse.success(BookingRoomRespone.fromBookingRoom(bookingRoom), "thanh cong", HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public ApiResponse<List<BookingRoomRespone>> getAllBookingRoom() {
        List<BookingRoom> bookingRooms = bookingRoomService.getAllBookingRooms();
        return ApiResponse.success(bookingRooms.stream().map(BookingRoomRespone::fromBookingRoom).toList(),
                "thanh cong", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ApiResponse<BookingRoomRespone> getBookingRoomById(@PathVariable Long id) {
        return ApiResponse.success(BookingRoomRespone.fromBookingRoom(bookingRoomService.getBookingRoomById(id)),
                "thanh cong", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ApiResponse<BookingRoomRespone> updateBookingRoom(@Valid @RequestBody BookingRoomDTO bookingRoomDTO,
            @PathVariable Long id) {
        BookingRoom bookingRoom = bookingRoomService.updateBookingRoom(id, bookingRoomDTO);
        return ApiResponse.success(BookingRoomRespone.fromBookingRoom(bookingRoom), "thanh cong", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBookingRoom(@PathVariable Long id) {
        bookingRoomService.deleteBookingRoom(id);
        return ResponseEntity.ok(String.format("thanh cong"));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/page")
    public ApiResponse<PaginationResponse<BookingRoomRespone>> getAllInvoicePage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        PageRequestDTO pageRequest = new PageRequestDTO(page, size, sortBy, sortDirection);
        var pageBookingRoom = bookingRoomService.getAllBookingRoomPage(pageRequest.toPageable());
        PaginationResponse<BookingRoomRespone> paginationResponse = PaginationResponse
                .fromPage(pageBookingRoom.map(BookingRoomRespone::fromBookingRoom));
        return ApiResponse.success(paginationResponse, "thanh cong");
    }
}
