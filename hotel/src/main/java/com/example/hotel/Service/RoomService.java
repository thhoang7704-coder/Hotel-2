package com.example.hotel.Service;

import java.util.List;
import java.util.Optional;

import com.example.hotel.DTO.RoomDTO;
import com.example.hotel.Model.Room;

public interface RoomService {
    Room createRoom(RoomDTO roomDTO);

    List<Room> getAllRooms();

    void deleteRoom(Long id) throws Exception;

    Room updateRoom(Long id, RoomDTO roomDTO);

    Room getRoomById(Long id);
}
