package mx.com.bhit.micro.service;

import mx.com.bhit.micro.model.Booking;
import org.bson.Document;

import java.util.Optional;

public interface BookingServiceI {


    Optional<Document> create(Booking booking);

}
