package com.spring_ai.SpringAi.tools;

import com.spring_ai.SpringAi.dtos.BookingResponse;
import com.spring_ai.SpringAi.dtos.BookingsListResponse;
import com.spring_ai.SpringAi.entity.BookingStatus;
import com.spring_ai.SpringAi.entity.FlightBooking;
import com.spring_ai.SpringAi.service.FlightBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import javax.print.attribute.standard.MediaSize;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor

public class FlightBookingTool {

    private final FlightBookingService flightBookingService;
    @Tool(
            name = "create_new_booking",
            description = "Use this took to create the new booking"
    )
    public BookingResponse createBooking(
            @ToolParam(description = "The userId for which the booking is made like (eg:liki)")
            String userId,
            @ToolParam(description = "The destination of flight booking (eg:london or america)")
            String destination,
            @ToolParam(description = "The departureTime of flight booking (eg:departuretime)")
            Instant departureTime)
    {
         FlightBooking flightBooking = flightBookingService.createBooking(userId,destination,departureTime);
         BookingResponse bookingResponse = new BookingResponse(
                flightBooking.getId(),
                flightBooking.getDestination(),
                 flightBooking.getDepartureTime(),
                 flightBooking.getBookingStatus()
         );
         return bookingResponse;
    }


    @Tool(name = "get_user_bookings",
    description = "fetch all the flight bookings for user")
    public BookingsListResponse getUserBookings(
            @ToolParam(description = "the user id of the user which is used to fetch Flight Bookings")
            String userId)
    {
        List<FlightBooking> flightBookings = flightBookingService.getUserBookings(userId);
        List<BookingResponse> bookingsListResponse =flightBookings.stream()
                .map(flightBooking ->
                    new BookingResponse(
                            flightBooking.getId(),
                            flightBooking.getDestination(),
                            flightBooking.getDepartureTime(),
                            flightBooking.getBookingStatus()
                    )
                )
                .collect(Collectors.toList());

        String message = bookingsListResponse.isEmpty()?"No flight bookings found":"Find the below flight bookings";
        return new BookingsListResponse(bookingsListResponse,message);

    }


   @Tool(
           name = "update_flight_bookings",
           description = "update the flight bookings for the user"
   )
    public BookingResponse updateBookingStatus(
            @ToolParam(description = "flight id which is need to be udpated")
            Long bookingId,
            @ToolParam(description = "the user id of the user who wants to update")
            String userId,
            @ToolParam(description = "the new Booking status like for eg: cancelled as per booking status enum")
            BookingStatus newStatus) {
       FlightBooking flightBooking = flightBookingService.updateBookingStatus(bookingId, userId, newStatus);
       BookingResponse bookingResponse = new BookingResponse(
               flightBooking.getId(),
               flightBooking.getDestination(),
               flightBooking.getDepartureTime(),
               flightBooking.getBookingStatus()
       );
       return bookingResponse;
   }
}
