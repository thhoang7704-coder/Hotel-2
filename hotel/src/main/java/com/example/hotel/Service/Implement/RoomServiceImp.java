package com.example.hotel.Service.Implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.hotel.DTO.RoomDTO;
import com.example.hotel.Model.Room;
import com.example.hotel.Repository.RoomRepository;
import com.example.hotel.Service.RoomService;
import com.example.hotel.exception.ResourceNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoomServiceImp implements RoomService {
    private final RoomRepository roomRepository;

    @Override
    public Room createRoom(RoomDTO roomDTO) {
        try {
            Room room = Room.builder()
                    .idRoom(roomDTO.getIdRoom())
                    .statusRoom(roomDTO.getStatusRoom())
                    .capacity(roomDTO.getCapacity())
                    .typeRoom(roomDTO.getTypeRoom())
                    .price(roomDTO.getPrice())
                    .build();
            roomRepository.save(room);
            return room;
        } catch (Exception e) {
            throw new RuntimeException("Không thể tạo user" + e.getMessage());
        }
    }

    @Override
    public List<Room> getAllRooms() {
        List<Room> listRoom = roomRepository.findAll();
        return listRoom;
    }

    @Override
    public void deleteRoom(Long id) throws Exception {
        roomRepository.deleteById(id);
    }

    @Override
    public Room getRoomById(Long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("ko thay"));
        return room;
    }

    @Override
    public Room updateRoom(Long id, RoomDTO roomDTO) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("ko thay id"));
        room.setIdRoom(roomDTO.getIdRoom());
        room.setCapacity(roomDTO.getCapacity());
        room.setPrice(roomDTO.getPrice());
        room.setTypeRoom(roomDTO.getTypeRoom());
        room.setStatusRoom(roomDTO.getStatusRoom());
        return roomRepository.save(room);
    }

    @Override
    public Page<Room> getAllRoomPaged(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

}
