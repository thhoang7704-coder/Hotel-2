package com.example.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
