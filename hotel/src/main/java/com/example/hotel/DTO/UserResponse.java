package com.example.hotel.DTO;

import java.sql.Date;

import com.example.hotel.Model.User;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private Date dob;
    private String status;
    private String role;
    private String phone;
    private String identifier;
    private String passwordHash;

    public static UserResponse fromUser(User user) {
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .dob(user.getDob())
                .status(user.getStatus())
                .role(user.getRole())
                .phone(user.getPhone())
                .identifier(user.getIdentifier())
                .passwordHash(user.getPasswordHash())
                .build();
        return userResponse;
    }
}
