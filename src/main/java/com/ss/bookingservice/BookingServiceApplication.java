package com.ss.bookingservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class BookingServiceApplication {

	public static void main(String[] args)
	{
		new SpringApplicationBuilder()
				.profiles("dev")
				.sources(BookingServiceApplication.class)
				.run(args);
	}
}
