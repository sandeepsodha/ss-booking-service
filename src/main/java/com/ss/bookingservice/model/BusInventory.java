package com.ss.bookingservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class BusInventory {
    private Long id;

    private Long busNumber;
    private int availableSeats;
    private LocalDate lastUpdatedDate;
}
