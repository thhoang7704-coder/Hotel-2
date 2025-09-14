package com.example.hotel.DTO;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDTO {
    private Long idRoom;
    private String statusRoom;
    private int capacity; // sức chứa
    private String typeRoom;
    private double price;
}
