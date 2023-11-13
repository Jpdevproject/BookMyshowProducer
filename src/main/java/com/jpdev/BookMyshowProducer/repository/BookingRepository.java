package com.jpdev.BookMyshowProducer.repository;

import com.jpdev.BookMyshowProducer.entity.BookingDetails;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<BookingDetails,Long> {
}
