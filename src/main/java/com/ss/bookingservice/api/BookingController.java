package com.ss.bookingservice.api;

import com.ss.bookingservice.config.ActiveMQConfiguration;
import com.ss.bookingservice.entity.Booking;
import com.ss.bookingservice.jms.PublisherTextService;
import com.ss.bookingservice.model.BusInventory;
import com.ss.bookingservice.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    @Autowired
    private WebClient webClient;

    @Autowired
    private PublisherTextService publisherTextService;

    @GetMapping("/")
    public List<Booking> getAllBookings() {

        return bookingService.getAllBookings();
    }

    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {

        System.out.println("getBookingById " + id);

        return bookingService.findById(id);
    }

    @GetMapping("/busnumber/{busNumber}")
    public Booking getBookingByBusNumber(@PathVariable Long busNumber) {

        return bookingService.findByBusNumber(busNumber);
    }

    @PostMapping("/")
    public Booking createBooking(@RequestBody Booking booking) {

        System.out.println("Booking Object " + booking.toString());

        BusInventory busInventoryResp = webClient.get()
                .uri("localhost:9072/inventory-service/businventorys/busnumber/" + booking.getBusNumber())
                .retrieve()
                .bodyToMono(BusInventory.class)
                .block();

        assert busInventoryResp != null;
        log.info("received response from web client" + busInventoryResp.toString());

        if(busInventoryResp.getAvailableSeats() >= booking.getNumberOfSeats()){

            Booking createdBooking = bookingService.createBooking(booking);
            booking.setStatus("pending");
            publisherTextService.publishText("Booking Number created and waiting for payment "+ createdBooking.getBookingNumber());
            return createdBooking;
        }
        else{
            return null;
        }
    }



    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking updatedBooking) {
        return bookingService.updateBooking(id,updatedBooking);
    }

    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }
}
