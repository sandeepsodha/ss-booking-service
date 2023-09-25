package com.ss.bookingservice.service;

import com.ss.bookingservice.entity.Booking;
import com.ss.bookingservice.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking findById(Long id) {
        return bookingRepository.findById(id).orElse(null);
    }

    public Booking createBooking(@RequestBody Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setBookingNumber(updatedBooking.getBookingNumber());
                    booking.setBusNumber(updatedBooking.getBusNumber());
                    booking.setBookingDate(updatedBooking.getBookingDate());
                    booking.setSource(updatedBooking.getSource());
                    booking.setDestination(updatedBooking.getDestination());
                    booking.setNumberOfSeats(updatedBooking.getNumberOfSeats());
                    booking.setStatus(updatedBooking.getStatus());
                    return bookingRepository.save(booking);
                })
                .orElse(null);
    }

    public void deleteBooking(@PathVariable Long id) {
        bookingRepository.deleteById(id);
    }
}
