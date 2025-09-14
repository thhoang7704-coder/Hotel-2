package com.example.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hotel.Model.BookingRoom;

@Repository
public interface BookingRoomRepository extends JpaRepository<BookingRoom, Long> {

}
