package com.jpdev.BookMyshowProducer.controller;

import com.jpdev.BookMyshowProducer.dto.BookingDto;
import com.jpdev.BookMyshowProducer.entity.BookingDetails;
import com.jpdev.BookMyshowProducer.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("rest/api/booking")
public class BookMyShowController {

    @Autowired
    private BookingService bookingService;


    @PostMapping
    public BookingDetails bookTicket(@RequestBody BookingDto bookingDto){
        return bookingService.bookTickets(bookingDto)
                .orElse(null);
    }

    @PutMapping("/{id}")
    public BookingDetails updateTicket(@RequestBody BookingDto bookingDto, @PathVariable Long id){
        return bookingService.updateTickets(bookingDto,id)
                .orElse(null);
    }

    @GetMapping("/{id}")
    public BookingDto getTicketById(@PathVariable Long id){
        return bookingService.getBookingsById(id)
                .orElse(null);
    }

    @GetMapping
    public List<BookingDto> getAllTicket(){
        return bookingService.getAllBookings()
                .orElse(null);
    }

    @DeleteMapping("/{id}")
    public String cancelBookingById(@PathVariable Long id){
        return bookingService.cancelBooking(id)
                .orElse(null);
    }

}
