package com.example.hotel.DTO;

import java.sql.Date;

import com.example.hotel.Model.User;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserResponse {
    public String name;
    public String email;
    public Date dob;
    public String status;
    public String role;

    public static UserResponse fromUser(User user) {
        UserResponse userResponse = UserResponse.builder()
                .name(user.getName())
                .email(user.getEmail())
                .dob(user.getDob())
                .status(user.getStatus())
                .role(user.getRole())
                .build();
        return userResponse;
    }
}
