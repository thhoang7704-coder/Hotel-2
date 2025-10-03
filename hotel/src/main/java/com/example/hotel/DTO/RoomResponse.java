package com.example.hotel.DTO;

import com.example.hotel.Model.Room;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoomResponse {

    private Long id;
    private Long idRoom;
    private String statusRoom;
    private int capacity; // sức chứa
    private String typeRoom;
    private double price;

    public static RoomResponse fromRoom(Room room) {
        RoomResponse roomResponse = RoomResponse.builder()
                .idRoom(room.getIdRoom())
                .statusRoom(room.getStatusRoom())
                .capacity(room.getCapacity())
                .typeRoom(room.getTypeRoom())
                .price(room.getPrice())
                .id(room.getId())
                .build();
        return roomResponse;
    }
}
