package com.example.hotel.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.ApiResponse;
import com.example.hotel.DTO.PageRequestDTO;
import com.example.hotel.DTO.PaginationResponse;
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

    @CrossOrigin(origins = "*")
    @PostMapping("/")
    public ApiResponse<UserResponse> createUser(@Valid @RequestBody UserDTO userDTO, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage).toList();

            return ApiResponse.error(errorMessages);
        }
        User user = userService.createUser(userDTO);
        return ApiResponse.success(UserResponse.fromUser(user), "Thanh cong", HttpStatus.CREATED);

    }

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public ApiResponse<List<UserResponse>> getAllUsers() {
        return ApiResponse.success(userService.getAllUsers().stream().map(UserResponse::fromUser).toList(),
                "thanh cong", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/{id}")
    public ApiResponse<User> getUserById(@PathVariable Long id) {
        return ApiResponse.success(userService.getUserById(id), "thanh cong", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping("/{id}")
    public ApiResponse<?> deleteUser(@PathVariable Long id) throws Exception {

        userService.deleteUser(id);
        return ApiResponse.noContent("Đã xóa thành công");
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/{id}")
    public ApiResponse<UserResponse> updateUser(@Valid @RequestBody UserDTO userDTO, @PathVariable Long id)
            throws Exception {
        User user = userService.updateUser(id, userDTO);
        return ApiResponse.success(UserResponse.fromUser(user), "thanh cong", HttpStatus.OK);
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/page")
    public ApiResponse<PaginationResponse<UserResponse>> getAllUsersPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        PageRequestDTO pageRequest = new PageRequestDTO(page, size, sortBy, sortDirection);
        var pageUsers = userService.getAllUsersPaged(pageRequest.toPageable());
        PaginationResponse<UserResponse> paginationResponse = PaginationResponse.fromPage(
                pageUsers.map(UserResponse::fromUser));

        return ApiResponse.success(paginationResponse, "Lấy danh sách có phân trang thành công");
    }

    @GetMapping("/emailandphone/")
    public ApiResponse<UserResponse> getUserByEmailAndPhone(
            @RequestParam String email,
            @RequestParam String phone

    ) {
        return ApiResponse.success(UserResponse.fromUser(userService.getByEmailAndPhone(email, phone)), "thanh cong",
                HttpStatus.OK);
    }
}
