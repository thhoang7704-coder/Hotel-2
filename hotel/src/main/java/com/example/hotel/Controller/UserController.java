package com.example.hotel.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.BookingRoomRespone;
import com.example.hotel.DTO.UserDTO;
import com.example.hotel.DTO.UserResponse;
import com.example.hotel.Model.User;
import com.example.hotel.Service.UserService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    public final UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage).toList();

            return ResponseEntity.badRequest().body(errorMessages);
        }
        User user = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body((UserResponse.fromUser(user)));

    }

    @GetMapping("/")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers().stream().map(UserResponse::fromUser).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(UserResponse.fromUser(userService.getUserById(id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);
        return ResponseEntity.ok(String.format("xóa thành công"));
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id) throws Exception {
        User user = userService.updateUser(id, userDTO);
        return ResponseEntity.ok(user);
    }
}
