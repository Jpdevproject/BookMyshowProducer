package com.jpdev.BookMyshowProducer.service;

import com.jpdev.BookMyshowProducer.dto.BookingDto;
import com.jpdev.BookMyshowProducer.entity.BookingDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface BookingService {
    Optional<BookingDetails> bookTickets(BookingDto bookingDto);
    Optional<BookingDetails> updateTickets(BookingDto bookingDto,Long id);
    Optional<BookingDto> getBookingsById(Long id);
    Optional<List<BookingDto>> getAllBookings();
    Optional<String> cancelBooking(Long id);
}
