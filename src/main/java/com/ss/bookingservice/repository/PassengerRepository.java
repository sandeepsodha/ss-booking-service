package com.ss.bookingservice.repository;

import com.ss.bookingservice.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

    List<Passenger> findByBookingNumber(Long bookingNumber);

}