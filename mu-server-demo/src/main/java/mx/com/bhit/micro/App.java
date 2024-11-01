package mx.com.bhit.micro;

import com.fasterxml.jackson.jakarta.rs.json.JacksonJsonProvider;
import io.muserver.MuServer;
import io.muserver.MuServerBuilder;
import io.muserver.rest.RestHandlerBuilder;
import mx.com.bhit.micro.api.BookingController;

/**
 * Booking
 */
public class App {

    public static void main(String[] args) {

        JacksonJsonProvider jacksonJsonProvider = new JacksonJsonProvider();
        // Configuración del servidor MuServer
        MuServer server = MuServerBuilder
                .httpServer()
                .withHttpPort(8080)
                .addHandler(
                        RestHandlerBuilder
                                .restHandler(new BookingController())
                                .addCustomWriter(jacksonJsonProvider)
                                .addCustomReader(jacksonJsonProvider)
                )
                .start();
        System.out.println("Server started at " + server.uri().resolve("/bookings"));
    }

}
