package com.jpdev.BookMyshowProducer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name="booking_details")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class BookingDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;
    @Column(name = "movie_name")
    private String movieName;
    @Column(name = "booking_date")
    private Date bookingDate;
    @Column(name = "show_date")
    private Date showDate;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "numbers_of_ticket")
    private Integer numbersOfTicket;
}
