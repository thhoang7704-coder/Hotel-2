package com.example.hotel.Service;

import java.util.List;

import com.example.hotel.DTO.UserDTO;
import com.example.hotel.Model.User;

public interface UserService {
    User createUser(UserDTO userDTO);

    List<User> getAllUsers();

    User getUserById(Long id);

    void deleteUser(Long id) throws Exception;

    User updateUser(Long id, UserDTO userDTO) throws Exception;
}
