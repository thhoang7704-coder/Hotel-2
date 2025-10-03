package com.example.hotel.Util;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Component;

import com.example.hotel.Model.Room;
import com.example.hotel.Repository.RoomRepository;
import com.github.javafaker.Faker;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class DataGenerateUtil {

    private final Faker faker = new Faker();
    private final RoomRepository repository;

    public void generateRooms(int count) {
        List<Room> rooms = IntStream.range(0, count)
                .mapToObj(i -> Room.builder()
                        .idRoom((long) faker.number().numberBetween(101, 999))
                        .typeRoom(faker.options().option("REGULAR", "SILVER", "GOLD", "VIP"))
                        .capacity(faker.options().option(1, 2, 3, 4))
                        .price(faker.number().randomDouble(200000, 500000, 1000000))
                        .statusRoom(faker.options().option("AVAILABLE", "OCCUPIED", "RESERVED", "MAINTENANCE"))
                        .build())
                .collect(Collectors.toList());

        for (Room room : rooms) {
            repository.save(room);
        }

    }

}
