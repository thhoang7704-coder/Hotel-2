package com.example.hotel.Service.Implement;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.hotel.DTO.UserDTO;
import com.example.hotel.Model.User;
import com.example.hotel.Repository.UserRepository;

import com.example.hotel.Service.UserService;
import com.example.hotel.exception.ResourceNotFoundException;

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

        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Không tìm thấy với id  = %d", id)));
    }

    @Override
    public void deleteUser(Long id) throws Exception {
        userRepository.deleteById(id);

    }

    @Override
    public User updateUser(Long id, UserDTO userDTO) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
        user.setName(userDTO.getName());
        user.setDob(userDTO.getDob());
        user.setEmail(userDTO.getEmail());
        user.setEmail(userDTO.getPhone());
        user.setIdentifier(userDTO.getIdentifier());
        user.setPasswordHash(userDTO.getPasswordHash());
        user.setRole(userDTO.getRole());
        user.setStatus(userDTO.getStatus());

        return userRepository.save(user);

    }

    @Override
    public Page<User> getAllUsersPaged(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User getByEmailAndPhone(String email, String phone) {

        return userRepository.findByEmailAndPhone(email, phone);

    }

}
