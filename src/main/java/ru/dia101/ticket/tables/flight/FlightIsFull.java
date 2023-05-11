package ru.dia101.ticket.tables.flight;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.dia101.ticket.users.User;
import ru.dia101.ticket.tables.airline.Airline;
import ru.dia101.ticket.tables.city.City;
import ru.dia101.ticket.tables.plane.Plane;

import java.util.Date;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class FlightIsFull {
    private Long id;
    private String flightNumber;
    private User user;
    private Airline airline;
    private City departureCity;
    private City arrivalCity;
    private Plane plane;
    private String departureTime;
    private String arrivalTime;
}
