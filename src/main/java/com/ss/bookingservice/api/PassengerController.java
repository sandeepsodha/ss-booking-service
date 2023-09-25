package com.ss.bookingservice.api;

import com.ss.bookingservice.entity.Booking;
import com.ss.bookingservice.entity.Passenger;
import com.ss.bookingservice.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @GetMapping("/")
    public List<Passenger> getAllPassengers() {
        return passengerService.getAllPassengers();
    }

    @GetMapping("/{id}")
    public Passenger getBookingById(@PathVariable Long id) {
        return passengerService.findById(id);
    }

    @GetMapping("/bookingNumber/{bookingNumber}")
    public List<Passenger> getPassengerByBookingNumber(@PathVariable Long bookingNumber) {
        return passengerService.findPassengersByBookingNumber(bookingNumber);
    }

    @PostMapping("/")
    public Passenger createPassenger(@RequestBody Passenger Passenger) {
        return passengerService.createPassenger(Passenger);
    }

    @PutMapping("/{id}")
    public Passenger updatePassenger(@PathVariable Long id, @RequestBody Passenger updatedPassenger) {
        return passengerService.updatePassenger(id,updatedPassenger);
    }

    @DeleteMapping("/{id}")
    public void deletePassenger(@PathVariable Long id) {
        passengerService.deletePassenger(id);
    }


}
