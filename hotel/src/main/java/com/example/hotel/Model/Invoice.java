package com.example.hotel.Model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "invoices")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "bookingRoomId")
    private BookingRoom bookingRoom;
    @Column(name = "methodPay")
    private String methodPay;
    @Column(name = "datePayment")
    private Date datePayment;
    @Column(name = "totalCost")
    private double totalCost;
    @Column(name = "status")
    private String status;
    @Column(name = "paymentStatus")
    private String paymentStatus;

}
