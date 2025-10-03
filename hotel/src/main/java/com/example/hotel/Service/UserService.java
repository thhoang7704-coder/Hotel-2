package com.example.hotel.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.hotel.DTO.UserDTO;
import com.example.hotel.Model.User;

public interface UserService {
    User createUser(UserDTO userDTO);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getByEmailAndPhone(String email, String phone);

    void deleteUser(Long id) throws Exception;

    User updateUser(Long id, UserDTO userDTO) throws Exception;

    Page<User> getAllUsersPaged(Pageable pageable);
}
