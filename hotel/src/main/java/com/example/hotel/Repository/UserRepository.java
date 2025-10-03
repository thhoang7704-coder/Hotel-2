package com.example.hotel.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hotel.Model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByPhone(String phone);

    User findByEmail(String phone);

    User findByEmailAndPhone(String email, String phone);
}
