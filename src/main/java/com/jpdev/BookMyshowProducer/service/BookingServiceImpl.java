package com.jpdev.BookMyshowProducer.service;

import com.jpdev.BookMyshowProducer.dto.BookingDto;
import com.jpdev.BookMyshowProducer.entity.BookingDetails;
import com.jpdev.BookMyshowProducer.repository.BookingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService{
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public Optional<BookingDetails> bookTickets(BookingDto bookingDto) {
        if(ObjectUtils.isEmpty((bookingDto))){
            return Optional.empty();
        }
        return Optional.of(bookingRepository.save(BookingDetails.builder()
                .bookingDate(bookingDto.getBookingDate())
                .customerName(bookingDto.getCustomerName())
                .movieName(bookingDto.getMovieName())
                .numbersOfTicket(bookingDto.getNumbersOfTicket())
                .showDate(bookingDto.getShowDate())
                .build()));
    }

    @Override
    public Optional<BookingDetails> updateTickets(BookingDto bookingDto, Long id) {
        var booking = bookingRepository.findById(id);
        if(booking.isPresent()){
            BookingDetails bookingDetails = booking.get();
            BeanUtils.copyProperties(bookingDto,bookingDetails);
            return Optional.of(bookingDetails);
        }
       return Optional.empty();
    }

    @Override
    public Optional<BookingDto> getBookingsById(Long id) {
        var booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            BookingDetails bookingDetails = booking.get();
            return Optional.of(BookingDto.builder()
                    .bookingId(id)
                    .bookingDate(bookingDetails.getBookingDate())
                    .customerName(bookingDetails.getCustomerName())
                    .movieName(bookingDetails.getMovieName())
                    .showDate(bookingDetails.getShowDate())
                    .numbersOfTicket(bookingDetails.getNumbersOfTicket())
                    .build());
        }
        return Optional.empty();
    }

    @Override
    public Optional<List<BookingDto>> getAllBookings() {
        var bookings = (List<BookingDetails>) bookingRepository.findAll();
        if (bookings.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(bookings.stream()
                .map(book -> BookingDto.builder()
                        .bookingId(book.getBookingId())
                        .bookingDate(book.getBookingDate())
                        .numbersOfTicket(book.getNumbersOfTicket())
                        .movieName(book.getMovieName())
                        .customerName(book.getCustomerName())
                        .showDate(book.getShowDate())
                        .build()).collect(Collectors.toList()));
    }

    @Override
    public Optional<String> cancelBooking(Long id) {
        bookingRepository.deleteById(id);
        return Optional.of("Booking with id " + id + " deleted");
    }
}
