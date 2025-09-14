package com.example.hotel.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.BookingRoomRespone;
import com.example.hotel.DTO.RoomDTO;
import com.example.hotel.DTO.RoomResponse;
import com.example.hotel.DTO.UserResponse;
import com.example.hotel.Model.Room;
import com.example.hotel.Repository.RoomRepository;
import com.example.hotel.Service.RoomService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @PostMapping("/")
    public ResponseEntity<?> createRoom(@Valid @RequestBody RoomDTO roomDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage).toList();

            return ResponseEntity.badRequest().body(errorMessages);
        }

        Room room = roomService.createRoom(roomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(RoomResponse.fromRoom(room));
    }

    @GetMapping("/")
    public ResponseEntity<?> getAllRoom() {
        return ResponseEntity.ok(roomService.getAllRooms().stream().map(RoomResponse::fromRoom).toList());
    }

    @DeleteMapping("{/id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) throws Exception {
        roomService.deleteRoom(id);
        return ResponseEntity.ok(String.format("xoa thanh cong"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(RoomResponse.fromRoom(roomService.getRoomById(id)));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateRoom(@Valid @RequestBody RoomDTO roomDTO, @PathVariable Long id) {
        Room room = roomService.updateRoom(id, roomDTO);
        return ResponseEntity.ok(room);
    }
}
