package com.example.hotel.Controller;

import java.util.List;

import org.apache.catalina.connector.Response;
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
import com.example.hotel.DTO.PageRequestDTO;
import com.example.hotel.DTO.PaginationResponse;
import com.example.hotel.DTO.RoomDTO;
import com.example.hotel.DTO.RoomResponse;

import com.example.hotel.Model.Room;

import com.example.hotel.Service.RoomService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

    @CrossOrigin(origins = "*")
    @PostMapping("/")
    public ApiResponse<RoomResponse> createRoom(@Valid @RequestBody RoomDTO roomDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage).toList();

            return ApiResponse.error(errorMessages);
        }

        Room room = roomService.createRoom(roomDTO);
        return ApiResponse.success(RoomResponse.fromRoom(room), "Thanh cong", HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public ApiResponse<List<RoomResponse>> getAllRoom() {
        return ApiResponse.success(roomService.getAllRooms().stream().map(RoomResponse::fromRoom).toList(),
                "thanh cong", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRoom(@PathVariable Long id) throws Exception {
        roomService.deleteRoom(id);
        return ResponseEntity.ok(String.format("xoa thanh cong"));
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ApiResponse<RoomResponse> getRoomById(@PathVariable Long id) {
        return ApiResponse.success(RoomResponse.fromRoom(roomService.getRoomById(id)), "Thanh cong", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ApiResponse<RoomResponse> updateRoom(@Valid @RequestBody RoomDTO roomDTO, @PathVariable Long id) {
        Room room = roomService.updateRoom(id, roomDTO);
        return ApiResponse.success(RoomResponse.fromRoom(room), "thanh cong", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/page")
    public ApiResponse<PaginationResponse<RoomResponse>> getAllRoomPaged(@RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        PageRequestDTO pageRequest = new PageRequestDTO(page, size, sortBy, sortDirection);
        var pageRoom = roomService.getAllRoomPaged(pageRequest.toPageable());
        PaginationResponse<RoomResponse> paginationResponse = PaginationResponse
                .fromPage(pageRoom.map(RoomResponse::fromRoom));
        return ApiResponse.success(paginationResponse, "thanh cong");
    }
}
