package com.example.hotel.Model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Getter
@Setter
@Table(name = "bookingrooms")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "userId")
    private Long userId;
    @Column(name = "customerId")
    private Long customerId;
    @Column(name = "dayIn")
    private Date dayIn;
    @Column(name = "dayOut")
    private Date dayOut;
    @Column(name = "Status")
    private String Status;
    @Column(name = "sumCost")
    private double sumCost;
    @Column(name = "deposit")
    private double deposit; // tiền cọc
}
