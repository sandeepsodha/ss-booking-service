package com.ss.bookingservice.jms;

import com.ss.bookingservice.entity.Booking;
import com.ss.bookingservice.service.BookingService;
import com.ss.bookingservice.config.ActiveMQConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class TextSubscriber {

    private static final Logger log = LoggerFactory.getLogger(TextSubscriber.class);

    @Autowired
    private BookingService bookingService;
    @JmsListener(destination = ActiveMQConfiguration.BOOKING_TOPIC)
    public void receiveText(TextMessage textMessage) throws JMSException {
        log.info("Received booking details from Booking Service: " + textMessage.getText());

        Long busNumber = Long.valueOf(textMessage.getText().split("Updated Bus Inventory for Bus Number ")[1]);

        Booking booking = bookingService.findByBusNumber(busNumber);
        booking.setStatus("confirmed");
        bookingService.updateBooking(booking.getBookingNumber(),booking);
        log.info("Booking confirmed for bookingNumber " + booking.getBookingNumber());
    }
}
