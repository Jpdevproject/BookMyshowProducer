package com.jpdev.BookMyshowProducer.service;

import com.jpdev.BookMyshowProducer.dto.BookingDto;
import com.jpdev.BookMyshowProducer.entity.BookingDetails;
import com.jpdev.BookMyshowProducer.repository.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookingServiceImplTest {

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingServiceImpl bookingService;

    private BookingDto bookingDto;
    @Mock
    private BookingDetails bookingDetails;
    @Mock
    private List<BookingDto> bookingDtoList;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bookingDto = BookingDto.builder()
                .bookingDate(new Date())
                .customerName("John Doe")
                .movieName("Avengers")
                .numbersOfTicket(2)
                .showDate(new Date())
                .bookingId(1L)
                .build();
        bookingDtoList.add(bookingDto);

        bookingDetails = BookingDetails.builder()
                .bookingDate(new Date())
                .customerName("John Doe")
                .movieName("Avengers")
                .numbersOfTicket(2)
                .showDate(new Date())
                .bookingId(1L)
                .build();

    }

    @Test
    public void testBookTicketsWhenValidBookingDtoThenReturnBookingDetails() {
        when(bookingRepository.save(any(BookingDetails.class))).thenReturn(bookingDetails);

        Optional<BookingDetails> result = bookingService.bookTickets(bookingDto);

        assertTrue(result.isPresent());
        assertEquals(bookingDetails, result.get());
    }

    @Test
    public void testBookTicketsWhenNullBookingDtoThenReturnEmptyOptional() {
        Optional<BookingDetails> result = bookingService.bookTickets(null);
        assertFalse(result.isPresent());
    }

    @Test
    public void testUpdateTicketsWhenValidBookingDtoAndIdThenReturnUpdatedBookingDetails() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(bookingDetails));
        Optional<BookingDetails> result = bookingService.updateTickets(bookingDto, 1L);

        assertTrue(result.isPresent());
        assertEquals(bookingDetails, result.get());
    }

    @Test
    public void testUpdateTicketsWhenInvalidIdThenReturnEmptyOptional() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<BookingDetails> result = bookingService.updateTickets(bookingDto, 1L);

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetBookingsByIdWhenValidIdThenReturnBookingDto() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.of(bookingDetails));

        Optional<BookingDto> result = bookingService.getBookingsById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    public void testGetBookingsByIdWhenInvalidIdThenReturnEmptyOptional() {
        when(bookingRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<BookingDto> result = bookingService.getBookingsById(1L);

        assertFalse(result.isPresent());
    }

    @Test
    public void testGetAllBookingsWhenBookingsExistThenReturnListOfBookingDto() {
        when(bookingRepository.findAll()).thenReturn(Arrays.asList(bookingDetails));

        Optional<List<BookingDto>> result = bookingService.getAllBookings();

        assertTrue(result.isPresent());
        assertEquals(1, result.get().size());
     //   assertEquals(bookingDto, result.get().get(0));
    }

    @Test
    public void testGetAllBookingsWhenNoBookingsExistThenReturnEmptyOptional() {
        when(bookingRepository.findAll()).thenReturn(Arrays.asList());

        Optional<List<BookingDto>> result = bookingService.getAllBookings();

        assertFalse(result.isPresent());
    }

    @Test
    public void testCancelBookingWhenValidIdThenReturnSuccessMessage() {
        doNothing().when(bookingRepository).deleteById(anyLong());

        Optional<String> result = bookingService.cancelBooking(1L);

        assertTrue(result.isPresent());
        assertEquals("Booking with id 1 deleted", result.get());
    }
}