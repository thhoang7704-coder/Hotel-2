package com.example.hotel.Service.Implement;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.hotel.DTO.UserDTO;
import com.example.hotel.Model.User;
import com.example.hotel.Repository.UserRepository;

import com.example.hotel.Service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {
    private final UserRepository userRepository;

    @Override
    public User createUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .dob(userDTO.getDob())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .identifier(userDTO.getIdentifier())
                .passwordHash(userDTO.getPasswordHash())
                .role(userDTO.getRole())
                .status(userDTO.getStatus())
                .build();
        userRepository.save(user);
        return user;

    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User getUserById(Long id) {

        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("ko tìm thấy id"));
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        userRepository.deleteById(id);

    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
        User.builder()
                .name(userDTO.getName())
                .dob(userDTO.getDob())
                .email(userDTO.getEmail())
                .phone(userDTO.getPhone())
                .identifier(userDTO.getIdentifier())
                .passwordHash(userDTO.getPasswordHash())
                .role(userDTO.getRole())
                .status(userDTO.getStatus())
                .build();
        userRepository.save(user);
        return user;

    }

}
