package mx.com.bhit.micro.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import mx.com.bhit.micro.model.Booking;
import mx.com.bhit.micro.service.MongoBookingService;

import java.time.LocalDateTime;


@Path("/bookings")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookingController {

    private final MongoBookingService mongoBookingService = new MongoBookingService();

    // Endpoint para crear un nuevo booking
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response createBooking(Booking booking) {
        System.out.println("Creating Booking: ");
        booking.setId(System.currentTimeMillis());  // Usamos timestamp como ID
        booking.setStatus(Booking.BookingStatus.PENDING);
        return mongoBookingService.create(booking).isPresent() ?
                Response.status(Response.Status.CREATED).entity(booking).build() :
                Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
    }

    // Endpoint para obtener todos los bookings
    @GET
    @Produces("application/json")
    public Response getAllBookings() {
        return Response.ok(mongoBookingService.findAll()).build();
    }

}
