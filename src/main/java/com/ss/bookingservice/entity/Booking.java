package com.ss.bookingservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingNumber;
    private String busNumber;
    private LocalDate bookingDate;
    private String source;
    private String destination;
    private int numberOfSeats;
    private String status;
}
