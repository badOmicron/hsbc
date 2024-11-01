package mx.com.bhit.micro.service;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import mx.com.bhit.micro.model.Booking;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
public class MongoBookingService implements BookingServiceI {

    private static final String DB_NAME = "bookingdb";
    private static final String COLLECTION_NAME = "bookings";
    private static MongoClient mongoClient;
    private static MongoDatabase database;
    private static MongoCollection<Document> bookingCollection;

    // Conexión a MongoDB
    static {
        mongoClient = MongoClients.create("mongodb://database:27017");
        database = mongoClient.getDatabase(DB_NAME);
        bookingCollection = database.getCollection(COLLECTION_NAME);
    }


    public Optional create(Booking booking) {
        System.out.println("booking service");
        try {
            System.out.println("try");

            booking.setStartDate(LocalDateTime.now());
            booking.setEndDate(LocalDateTime.now());

            Document bookingDoc = new Document("id", booking.getId())
                    .append("userName", booking.getUserName())
                    .append("tableSize", booking.getTableSize())
                    .append("propertyId", booking.getPropertyId())
                    .append("startDate", booking.getStartDate().toString())
                    .append("endDate", booking.getEndDate().toString())
                    .append("status", booking.getStatus().toString())
                    .append("totalCost", booking.getTotalCost());

            bookingCollection.insertOne(bookingDoc);
            return Optional.of(bookingDoc);
        } catch (Exception e) {
            log.error("Something nasty happened", e);
            System.out.println("Something nasty happened:  " + e);
        }
        return Optional.empty();
    }


    public List<Booking> findAll() {
        List<Booking> bookings = new ArrayList<>();
        for (Document doc : bookingCollection.find()) {
            Booking booking = Booking.builder()
                    .id(doc.getLong("id"))
                    .userName(doc.getLong("userName"))
                    .propertyId(doc.getLong("propertyId"))
                    .startDate(LocalDateTime.parse(doc.getString("startDate")))
                    .endDate(LocalDateTime.parse(doc.getString("endDate")))
                    .status(Booking.BookingStatus.valueOf(doc.getString("status")))
                    .totalCost(doc.getDouble("totalCost"))
                    .build();
            bookings.add(booking);
        }
        return bookings;
    }
}
