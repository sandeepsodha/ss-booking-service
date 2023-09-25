package com.ss.bookingservice.service;

import com.ss.bookingservice.entity.Passenger;
import com.ss.bookingservice.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    public List<Passenger> getAllPassengers() {
        return passengerRepository.findAll();
    }

    public Passenger findById(Long id) {
        return passengerRepository.findById(id).orElse(null);
    }

    public List<Passenger> findPassengersByBookingNumber(Long bookingNumber) {
        return passengerRepository.findByBookingNumber(bookingNumber);
    }

    public Passenger createPassenger(@RequestBody Passenger Passenger) {
        return passengerRepository.save(Passenger);
    }

    public Passenger updatePassenger(@PathVariable Long id, @RequestBody Passenger updatedPassenger) {
        return passengerRepository.findById(id)
                .map(passenger -> {
                    passenger.setBookingNumber(updatedPassenger.getBookingNumber());
                    passenger.setUserId(updatedPassenger.getUserId());
                    return passengerRepository.save(passenger);
                })
                .orElse(null);
    }

    public void deletePassenger(@PathVariable Long id) {
        passengerRepository.deleteById(id);
    }
}
