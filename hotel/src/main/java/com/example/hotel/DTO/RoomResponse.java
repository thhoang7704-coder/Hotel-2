package com.example.hotel.DTO;

import com.example.hotel.Model.Room;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoomResponse {
    private Long idRoom;
    private String statusRoom;

    public static RoomResponse fromRoom(Room room) {
        RoomResponse roomResponse = RoomResponse.builder()
                .idRoom(room.getIdRoom())
                .statusRoom(room.getStatusRoom())
                .build();
        return roomResponse;
    }
}
