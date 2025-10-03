package com.example.hotel.DTO;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {
    @NotBlank(message = "Tên khách hàng không được để trống")
    @Size(min = 2, max = 50, message = "Tên khách hàng phải từ 2 đến 50 ký tự")
    private String name;

    @NotNull(message = "Ngày sinh không được null")
    @Past(message = "Ngày sinh phải ở quá khứ")
    private LocalDate dob;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "CMND/CCCD không được để trống")
    @Size(min = 9, max = 12, message = "CMND/CCCD phải từ 9 đến 12 số")
    private String identifier;
}
