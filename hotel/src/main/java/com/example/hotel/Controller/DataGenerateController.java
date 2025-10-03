package com.example.hotel.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hotel.DTO.ApiResponse;
import com.example.hotel.Model.Room;
import com.example.hotel.Util.DataGenerateUtil;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/data-generator")
@AllArgsConstructor
public class DataGenerateController {

    private final DataGenerateUtil dataGenerateUtil;

    @PostMapping("/room/{count}")
    @CrossOrigin("*")
    public ResponseEntity<?> generateRooms(@PathVariable int count) {
        {
            dataGenerateUtil.generateRooms(count);
            return ResponseEntity.ok("Tao du lieu mau thanh cong");
        }
    }
}
