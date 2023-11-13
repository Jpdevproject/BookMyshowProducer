package com.jpdev.BookMyshowProducer.dto;

import lombok.*;

import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class BookingDto {
    private Long bookingId;
    private String movieName;
    private Date bookingDate;
    private Date showDate;
    private String customerName;
    private Integer numbersOfTicket;
}
